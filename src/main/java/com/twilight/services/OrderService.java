package com.twilight.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.twilight.dataTransferObjects.ItemR;
import com.twilight.dataTransferObjects.Location;
import com.twilight.dataTransferObjects.OrderR;
import com.twilight.dataTransferObjects.Price;
import com.twilight.objects.Order;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {
    @Transactional
    public Optional<Map<String, Object>> create(String mobNo, OrderR orderDetails) throws Exception;

    @Transactional
    public List<Order> get(String mobNo, int page);

    Price getPrice(List<ItemR> items, Integer outletId, Location location);
}
