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
public class NacosController {
    private final NacosConfigResource nacosConfigResource;

    @GetMapping
    Map<String, String> getConfig(){
        var result = new HashMap<String, String>();
        result.put("serverAddr", nacosConfigResource.getServerAddr());
        result.put("prefix", nacosConfigResource.getPrefix());
        result.put("group", nacosConfigResource.getGroup());
        result.put("namespace", nacosConfigResource.getNamespace());

        return result;
    }
}
