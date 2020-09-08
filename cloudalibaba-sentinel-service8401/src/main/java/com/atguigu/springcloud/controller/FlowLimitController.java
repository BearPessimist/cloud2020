package com.atguigu.springcloud.controller;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA() {
        return "testA";
    }
    @GetMapping("/testB")
    public String testB() {

//        try {   //线程数直接失败
//            TimeUnit.MILLISECONDS.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info(Thread.currentThread().getName()+ "testB");
        return "testB";
    }

    //降级演示
    @GetMapping("/testC")
    public String test() {
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int i = 10 /0;
        log.info("testC,测试---异常比例");
        return "-------testC";
    }

    //异常数
    @GetMapping("/testD")
    public String testD() {
        int i = 10 /0;
        log.info("testD,测试---异常数");
        return "-------testD";
    }


    //热点key规则
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey") //添加自定义降级之后的方法
    public String test(@RequestParam(value = "p1",required = false)String s1,
                       @RequestParam(value = "p2",required = false)String p2) {
        return "----testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        return "----deal_testHotKey";
    }
}
