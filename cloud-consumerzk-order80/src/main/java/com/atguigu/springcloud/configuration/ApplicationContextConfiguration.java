package com.atguigu.springcloud.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfiguration {

    @Bean
    @LoadBalanced //负载均衡注解，多个服务提供者服务的情况下会切换不同的服务进行访问
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
