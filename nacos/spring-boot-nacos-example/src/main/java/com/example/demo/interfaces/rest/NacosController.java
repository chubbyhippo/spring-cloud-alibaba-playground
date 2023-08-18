package com.example.demo.interfaces.rest;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.example.demo.domain.NacosConfig;
import com.example.demo.interfaces.rest.dto.NacosConfigResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.alibaba.nacos.api.common.Constants.DEFAULT_GROUP;

@RestController
@RequestMapping("/nacos")
@RequiredArgsConstructor
public class NacosController {
    private final NacosConfig nacosConfig;
    private final NacosConfigDtoConverter nacosConfigDtoConverter;
    private final NacosConfigManager nacosConfigManager;

    @GetMapping("/bean")
    NacosConfigResource getConfig() {
        return nacosConfigDtoConverter.toNacosConfigResource(NacosConfig.builder()
                .serverAddr(nacosConfig.getServerAddr())
                .prefix(nacosConfig.getPrefix())
                .group(nacosConfig.getGroup())
                .namespace(nacosConfig.getNamespace())
                .build());
    }

    @GetMapping("/config")
    public String getConfig(@RequestParam("dataId") String dataId,
                            @RequestParam(value = "group", required = false) String group)
            throws NacosException {
        if (StringUtils.isEmpty(group)) {
            group = DEFAULT_GROUP;
        }
        ConfigService configService = nacosConfigManager.getConfigService();
        return configService.getConfig(dataId, group, 2000);
    }
}
