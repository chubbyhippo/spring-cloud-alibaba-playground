package com.example.demo.inflastructure.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AutoUpdateConfig {

    private final NacosConfigManager nacosConfigManager;
    public static final String DATA_ID = "nacos-config-example.properties";
    public static final String GROUP = "DEFAULT_GROUP";

    @PostConstruct
    public void init() throws NacosException {
        var configService = nacosConfigManager.getConfigService();

        configService.addListener(DATA_ID, GROUP, new Listener() {
            @Override
            public Executor getExecutor() {
                return Executors.newSingleThreadExecutor();
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                log.info("[dataId]:[" + DATA_ID + "],Configuration changed to:"
                         + configInfo);
            }
        });
    }
}
