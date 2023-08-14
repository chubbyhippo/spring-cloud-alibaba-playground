package com.example.demo.interfaces.rest;

import com.example.demo.domain.NacosConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/nacos/bean")
@RequiredArgsConstructor
public class NacosController {
    private final NacosConfig nacosConfig;

    @GetMapping
    Map<String, String> getConfig(){
        var result = new HashMap<String, String>();
        result.put("serverAddr", nacosConfig.getServerAddr());
        result.put("prefix", nacosConfig.getPrefix());
        result.put("group", nacosConfig.getGroup());
        result.put("namespace", nacosConfig.getNamespace());

        return result;
    }
}
