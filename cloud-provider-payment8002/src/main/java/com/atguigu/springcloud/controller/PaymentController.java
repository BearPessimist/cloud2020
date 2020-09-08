package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    //去处yaml里配置的port端口号，并查看最终操作结果的是哪个项目
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果: =>{}" + result);

        if (result > 0) {
            return new CommonResult(200,"插入成功"+serverPort,result);
        } else {
            return new CommonResult(444,"插入失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymetnById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        log.info("查询结果: =>{}" + result);
        if (result != null) {
            return new CommonResult(200,"查询成功"+serverPort,result);
        } else {
            return new CommonResult(444,"查询失败"+ id,null);
        }
    }

    /**
     *      测试自定义负载均衡算法的接口
     * @return
     */
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}

