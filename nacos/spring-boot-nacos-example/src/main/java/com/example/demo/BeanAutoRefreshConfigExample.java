package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/nacos/bean")
@RequiredArgsConstructor
public class BeanAutoRefreshConfigExample {
    private final NacosConfigInfo nacosConfigInfo;

    @GetMapping
    Map<String, String> getConfigInfo() {
        var result = new HashMap<String, String>();
        result.put("serverAddr", nacosConfigInfo.serverAddr());
        result.put("prefix", nacosConfigInfo.prefix());
        result.put("group", nacosConfigInfo.group());
        result.put("namespace", nacosConfigInfo.namespace());

        return result;
    }
}
