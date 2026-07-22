package com.twilight.serviceImpls;

import  java.time.Duration;

import com.twilight.dataTransferObjects.*;
import com.twilight.dataTransferObjects.Location;
import com.twilight.exceptions.NotDeliverableException;
import com.twilight.exceptions.NotFoundException;
import com.twilight.objects.*;
import com.twilight.repositories.CustomerRepository;
import com.twilight.repositories.FoodRepository;
import com.twilight.repositories.OrderRepository;
import com.twilight.repositories.OutletRepository;
import com.twilight.services.*;
import com.twilight.types.OrderStatus;
import com.twilight.types.PaymentMethod;
import com.twilight.types.PaymentStatus;
import com.twilight.utils.Constants;
import com.twilight.utils.GeoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;


import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    final CustomerRepository customerRepository;
    final OrderRepository orderRepository;
    final FoodRepository foodRepository;
    final PaymentService paymentService;
    final KafkaTemplate<String,Object> kafka ;
    final RedisTemplate<String,Object> redis;
    final OutletRepository  outletRepository;
    final ObjectMapper objectMapper;


    @Override
    public Optional<Map<String, Object>> create(String mobNo, OrderR orderDetails) throws Exception {
        List<Item> items = getItems(orderDetails.foods(), orderDetails.outletId());
        Outlet outlet = outletRepository.findById(orderDetails.outletId()).orElseThrow(NotFoundException::new);
        OutletLocation outletLocation = outlet.getLocation();
        double distance = GeoUtil.calculateDistance(orderDetails.address().latitude(),orderDetails.address().latitude(),outletLocation.getLatitude(),outletLocation.getLongitude());
        Price priceObj = getPrice(items,distance);
        double price = priceObj.total();


        Order order = Order.builder()
                .customer(customerRepository.findById(mobNo).orElseThrow(NotFoundException::new))
                .items(items)
                .deliveryLocation(new OrderLocation(orderDetails.address().latitude(),orderDetails.address().longitude(),orderDetails.address().label()))
                .outlet(outlet)
                .total(price)
                .deliveryCharge(priceObj.deliveryCharge()+10)
                .outletCharge(priceObj.total())
                .deliveryMobNo(orderDetails.deliveryMobNo())
                .paymentMethod(orderDetails.paymentMethod())
                .paymentStatus(PaymentStatus.pending)
                .status(OrderStatus.outlet_pending)
                .build();

        for(Item item: items){
            item.setOrder(order);
        }


        if(orderDetails.paymentMethod().equals(PaymentMethod.cash_on_delivery)){
            order = orderRepository.save(order);
            OutletOrderRequest orderRequest = new OutletOrderRequest(orderDetails.foods(),mobNo, order.getId(),orderDetails.address());
            kafka.send(Constants.NEW_ORDER_COD_TOPIC,orderRequest);
            return Optional.empty();
        }
        Map<String,Object> response = paymentService.createPayment(price,"IND",generateReceipt());
        order.setRazorpayOrderId((String)response.get("id"));
        orderRepository.save(order);
        return Optional.of(response);
    }

    @Override
    public List<Order> get(String mobNo, int page) {
        return orderRepository.findByCustomerMobNo(mobNo, PageRequest.of(page,10));
    }

    @Override
    public Price getPrice(List<ItemR> items, Integer outletId, Location location){
        OutletLocation outletLocation = outletRepository.findById(outletId).orElseThrow(NotFoundException::new).getLocation();
        double distanceInKm = GeoUtil.calculateDistance(location.latitude(),location.longitude(),outletLocation.getLatitude(),outletLocation.getLongitude());
        if(distanceInKm<Constants.MAXIMUM_DELIVERABLE_DISTANCE){
            throw new NotDeliverableException();
        }
        List<Integer> foodIds = items.stream().map(ItemR::foodId).toList();
        Map<Integer, Double> foods = getFoods(foodIds,outletId)
                .stream()
                .collect(
                        Collectors.toMap(
                                Food::getId,
                                Food::getPrice));
        double total =0;
        for(ItemR item : items){
            total = total + item.quantity()*foods.get(item.foodId());
        }
        return new Price(total ,distanceInKm * Constants.PER_KIL0METER_DELIVERY_CHARGE,Constants.BASE_CHARGE_AND_PLATFORM_FEE);
    }

    private List<Food> getFoods(List<Integer> foodIds, Integer outletId ){
        List<Integer> absentIds= new ArrayList<>();
        List<Food> foods = new ArrayList<>();
        for (Integer foodId : foodIds) {
            try {
                foods.add(objectMapper.convertValue(
                                 redis.opsForValue()
                                         .get(foodId.toString())
                                 ,Food.class));
            } catch (IllegalArgumentException e) {
                absentIds.add(foodId);
            }

        }
        List<Food> absentFoods = foodRepository.findAllByIdInAndOutletIdAndAvailableTrue(absentIds,outletId);
        absentFoods.forEach(food -> {redis.opsForValue().set(food.getId().toString(), food, Duration.ofMinutes(2));});
        foods.addAll(absentFoods);
        return foods;

    }

    private List<Item> getItems(List<ItemR> items, Integer outletId){
        Map<Integer,Food> foods = getFoods(items.stream().map(ItemR::foodId).toList(),outletId).stream()
                .collect(
                        Collectors.toMap(
                                Food::getId,
                                Function.identity()));
        List<Item> itemReturn = new ArrayList<>();
        for(ItemR item : items){
            Food food = foods.get(item.foodId());
            itemReturn.add(Item.builder().food(food).subtotal(item.quantity()*food.getPrice()).build());
        }
        return itemReturn;
    }

    private Price getPrice(List<Item> items, double distanceInKm){
        double total = 0;
        for (Item item : items) {
            total += item.getSubtotal();
        }
        return new Price(total,distanceInKm * Constants.PER_KIL0METER_DELIVERY_CHARGE,Constants.BASE_CHARGE_AND_PLATFORM_FEE );
    }

    private String generateReceipt(){
        return "twilight-" + UUID.randomUUID().toString().replace("-", "").substring(0,11);
    }

}
