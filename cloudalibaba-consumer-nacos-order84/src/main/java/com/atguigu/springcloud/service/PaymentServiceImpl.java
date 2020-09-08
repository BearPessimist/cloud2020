package com.atguigu.springcloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public CommonResult<Payment> pay(Long id) {

        return new CommonResult<>(444,"服务降级",new Payment(id,"errorSerial"));
    }
}






