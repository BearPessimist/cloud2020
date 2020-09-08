package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentId(@PathVariable("id") Long id) {
        CommonResult paymentById = paymentFeignService.getPaymentById(id);
        log.info("查询Id ===> {}" + paymentById);
        return paymentById;
    }

    //超时接口
    @GetMapping(value = "/consumer/feign/timeout")
    public String paymentFeignTimeout() {
//        openFeign-ribbon客户端默认等待1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }
}
