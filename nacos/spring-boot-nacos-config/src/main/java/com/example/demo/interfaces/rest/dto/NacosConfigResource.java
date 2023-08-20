package com.example.demo.interfaces.rest.dto;

import lombok.Builder;

@Builder
public record NacosConfigResource(

        String serverAddr,
        String prefix,
        String group,
        String namespace
) {
}
