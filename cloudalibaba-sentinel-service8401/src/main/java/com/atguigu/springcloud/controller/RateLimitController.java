package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult commonResult() {
        return new CommonResult(200,"按资源名称限流",new Payment(2020L,"serial001"));
    }
    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }


    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200,"按url限流",new Payment(2020L,"serial002"));
    }

    //CustomerBlockHandler

    @GetMapping("/rateLimit/CustomerBlockHandler")
    @SentinelResource(value = "CustomerBlockHandler",
    blockHandlerClass = CustomerBlockHandler.class,
    blockHandler = "handler2") //调用CustomerBlockHandler类里的handler2方法作为降级方法
    public CommonResult CustomerBlockHandler() {
        return new CommonResult(200,"自定义限流",new Payment(2020L,"serial003"));
    }
}







