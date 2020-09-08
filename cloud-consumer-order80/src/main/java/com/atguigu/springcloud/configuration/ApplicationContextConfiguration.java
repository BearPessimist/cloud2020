package com.atguigu.springcloud.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfiguration {

    @Bean
//    @LoadBalanced //使用这个注解提供负载均衡的功能，可以同时访问不同端口的服务提供者
    public RestTemplate getResTemplate() {

        return new RestTemplate();
    }
}
