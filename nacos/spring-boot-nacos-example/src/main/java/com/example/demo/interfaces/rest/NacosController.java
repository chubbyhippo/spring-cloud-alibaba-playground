package com.example.demo.interfaces.rest;

import com.example.demo.domain.NacosConfig;
import com.example.demo.interfaces.rest.dto.NacosConfigResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nacos")
@RequiredArgsConstructor
public class NacosController {
    private final NacosConfig nacosConfig;
    private final NacosConfigDtoConverter nacosConfigDtoConverter;

    @GetMapping("/bean")
    NacosConfigResource getConfig() {
        return nacosConfigDtoConverter.toNacosConfigResource(NacosConfig.builder()
                .serverAddr(nacosConfig.getServerAddr())
                .prefix(nacosConfig.getPrefix())
                .group(nacosConfig.getGroup())
                .namespace(nacosConfig.getNamespace())
                .build());
    }
}
