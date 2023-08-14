package com.example.demo;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@ConfigurationProperties(prefix = "spring.cloud.nacos.config")
@RefreshScope
@Data
public class NacosConfigResource {

    String serverAddr;
    String prefix;
    String group;
    String namespace;

}
