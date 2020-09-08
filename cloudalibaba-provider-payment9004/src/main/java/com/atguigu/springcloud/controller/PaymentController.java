package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PaymentController {

    @Value("${server.port}")
    public String serverPort;

    public static Map<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L,new Payment(1L,"12321312312312312"));
        hashMap.put(2L,new Payment(2L,"423423423432423423"));
        hashMap.put(3L,new Payment(3L,"234234545466465675"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> commonResult(@PathVariable("id")Long id) {
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200,"from mysql serverPort"+serverPort,payment);
        return result;
    }
}
