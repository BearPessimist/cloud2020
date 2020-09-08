package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     *      正常访问没有错误的方法
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {

        return "线程池 "+Thread.currentThread().getName()+ " paymentInfo_OK,id: "+id+"\t"+"O(∩_∩)O哈哈~";
    }

    //服务降级注解，并在里面指定降级的方法，设置限制超时的时间为3000，
    // 也就是3秒钟，如果超过了这个时间则调用，fallbackMethod指定的方法兜底
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {

        //暂停毫秒
        try {
            TimeUnit.SECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池 "+Thread.currentThread().getName()+ " paymentInfo_TimeOut,id: "+id+"\t"+"O(∩_∩)O哈哈~";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {

        return "线程池 "+Thread.currentThread().getName()+ " 系统繁忙，请稍后再试,id: "+id+"\t"+"T_T";
    }


    // 服务熔断 ↓ ↓ ↓
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",
            commandProperties = {
                @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启断路器
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //时间窗口期
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少关闭服务
            }
    )
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id 不能为负数");
        }
        String uuid = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号"+uuid;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能为负数，请稍后重试 T_T id:===>" + id;
    }
}
