package com.example.demo;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.nacos.config")
public record NacosConfigInfo(
        String serverAddr,
        String prefix,
        String group,
        String namespace
) {

}
