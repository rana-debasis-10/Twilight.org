package com.twilight.serviceImpls;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.twilight.dataTransferObjects.Payment;
import com.twilight.exceptions.SomethingWentWrongException;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.services.PaymentService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service

public class PaymentServiceImpl implements PaymentService {
    @Value("${razorpay.key.secret}")
    private String keySecret;
    @Value("${razorpay.key.id}")
    private String keyId;


    @Autowired
    KafkaTemplate<String,String> kafka;

    @Override
    public void verifyPayment(Payment payment) {
        System.out.println("verification invoked");
        JSONObject options = new JSONObject();
        try {
            options.put("razorpay_signature",payment.razorpaySignature());
            options.put("razorpay_order_id",payment.razorpayOrderId());
            options.put("razorpay_payment_id",payment.razorpayPaymentId());
        } catch (JSONException e) {
            throw new UnAuthorizedException(e.getMessage()
                    + "User is trying invalid payment",
                    "Invalid Confirmation");
        }
        try{
            if(Utils.verifyPaymentSignature(options, keySecret)){
                kafka.send(
                        "Notify-Outlet",
                        payment.razorpayOrderId()
                );
            }

        }
        catch (RazorpayException e){
           throw new UnAuthorizedException(
                   "User trying unauthorized payment",
                   e.getMessage());
        }
    }



    @Override
    public Map<String, Object> createPayment(Double total, String currency, String receipt) throws RazorpayException {
        Map<String, Object> response = new HashMap<>();

        try {

            RazorpayClient client = new RazorpayClient(keyId, keySecret);
            int amountInPaise = (int) Math.round(total * 100);

            JSONObject req = new JSONObject();
            req.put("amount", amountInPaise);
            req.put("currency", currency);
            req.put("receipt", receipt);

            Order order = client.orders.create(req);
            String id = order.get("id");

            response.put("id", id);
            response.put("key", keyId);
            response.put("amount",total);
            return response;
        } catch (RazorpayException e) {
           throw new SomethingWentWrongException(e.getMessage(),"Server is down currently");
        }

    }

}
