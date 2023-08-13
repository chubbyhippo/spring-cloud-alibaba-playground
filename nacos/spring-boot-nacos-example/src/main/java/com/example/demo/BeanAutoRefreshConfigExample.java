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
        result.put("serverAddr", nacosConfigInfo.getServerAddr());
        result.put("prefix", nacosConfigInfo.getPrefix());
        result.put("group", nacosConfigInfo.getGroup());
        result.put("namespace", nacosConfigInfo.getNamespace());

        return result;
    }
}
