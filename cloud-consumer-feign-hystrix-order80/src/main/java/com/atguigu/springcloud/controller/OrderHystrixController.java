package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod") //全局处理
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String ok = paymentHystrixService.paymentInfo_OK(id);
        return ok;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    //添加降级注解，如果访问服务提供者端超过1.5秒则报fallbackMethod 里指定的方法
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand //指定降级注解，利用上边的全局配置
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }
    //这个降级提示方法对应上边的paymentInfo_TimeOut方法
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是服务消费者，对方支付系统繁忙，请稍后再试，T_T T_T";
    }

    //下面是全局降级方法
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试";
    }

}
