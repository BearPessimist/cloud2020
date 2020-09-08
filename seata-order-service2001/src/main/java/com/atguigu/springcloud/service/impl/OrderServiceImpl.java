package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.service.AccountService;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    //通过feign远程调用其它微服务项目的接口
    @Autowired
    private StorageService storageService;
    @Autowired
    private AccountService accountService;

    @Override
    //只要发生了异常就回滚数据
    @GlobalTransactional(name = "create-order",rollbackFor = Exception.class)
    public void create(Order order) {

        //创建订单
        log.info("开始新建订单");
        orderDao.create(order);

        log.info("订单微服务开始调用，扣总数Count");
        //调用扣库存方法，传入订单的id和总数扣除
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("订单微服务开始调用账户，扣余额Money");

        //扣减账户余额
        //调用账户扣余额方法，传入订单的id和钱数扣除
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("订单微服务开始调用库存，做扣减end");


        //修改订单状态，1代表已完成
        log.info("修改订单状态开始");
        //调用修改方法，传入订单id和状态
        orderDao.update(order.getUserId(),0);
        log.info("修改订单状态结束");

        log.info("下单结束------");
   }
}
