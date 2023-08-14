package com.example.demo.interfaces.rest;

import com.example.demo.domain.NacosConfig;
import com.example.demo.interfaces.rest.dto.NacosConfigResource;
import org.springframework.stereotype.Component;

@Component
public class NacosConfigDtoConverter {

    NacosConfigResource toNacosConfigResource(NacosConfig nacosConfig) {
        return NacosConfigResource.builder()
                .serverAddr(nacosConfig.getServerAddr())
                .prefix(nacosConfig.getPrefix())
                .group(nacosConfig.getGroup())
                .namespace(nacosConfig.getNamespace())
                .build();
    }




}
