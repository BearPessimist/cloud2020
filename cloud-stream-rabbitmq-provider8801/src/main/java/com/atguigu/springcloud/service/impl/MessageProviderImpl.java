package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

@EnableBinding(Source.class) //定义消息提供者提供的信息
public class MessageProviderImpl implements MessageProvider {

    @Autowired
    private MessageChannel output; //变量名称对应配置文件bindings下面的output标签

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("---serial: " + serial);
        return null;
    }
}
