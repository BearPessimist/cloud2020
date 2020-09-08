package com.atguigu.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handler1(BlockException e) {
        return new CommonResult(200,"自定义限流，global，handler-----1 ");
    }

    public static CommonResult handler2(BlockException e) {
        return new CommonResult(200,"自定义限流，global，handler-----2 ");
    }
}
