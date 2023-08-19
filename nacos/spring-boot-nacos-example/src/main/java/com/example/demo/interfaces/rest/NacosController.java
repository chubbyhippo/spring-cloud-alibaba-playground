package com.example.demo.interfaces.rest;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.exception.NacosException;
import com.example.demo.domain.NacosConfig;
import com.example.demo.interfaces.rest.dto.NacosConfigResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getConfig")
    public String getConfig(@RequestParam("dataId") String dataId,
                            @RequestParam(value = "group", required = false) String group)
            throws NacosException {
        if (StringUtils.isEmpty(group)) {
            group = DEFAULT_GROUP;
        }
        var configService = nacosConfigManager.getConfigService();
        return configService.getConfig(dataId, group, 2000);
    }

    @PostMapping("/publishConfig")
    public boolean publishConfig(@RequestParam("dataId") String dataId,
                                 @RequestParam(value = "group", required = false) String group,
                                 @RequestParam("content") String content) throws NacosException {
        if (StringUtils.isEmpty(group)) {
            group = DEFAULT_GROUP;
        }
        var configService = nacosConfigManager.getConfigService();
        return configService.publishConfig(dataId, group, content);
    }
}


