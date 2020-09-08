package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //动态刷新注解
public class ConfigClientController {

    @Value("${server.port}") //注入项目端口号
    private String serverPort;

    @Value("${config.info}") //注入github上配置文件的属性
    private String configInfo;

    @GetMapping("/configInfo")
    public String configInfo() {
        return "serverPort：" + serverPort + "\t\n\n configInfo: " + configInfo;
    }
}
