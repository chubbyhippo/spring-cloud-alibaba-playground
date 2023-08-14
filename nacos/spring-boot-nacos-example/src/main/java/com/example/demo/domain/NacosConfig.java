package com.example.demo.domain;


import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@ConfigurationProperties(prefix = "spring.cloud.nacos.config")
@RefreshScope
@Data
@Builder
public class NacosConfig {

    String serverAddr;
    String prefix;
    String group;
    String namespace;

}
