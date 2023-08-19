package com.example.demo;

import com.example.demo.interfaces.rest.dto.NacosConfigResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests extends AbstractTestContainersSetup{
    @Autowired
    private WebTestClient client;

    @Test
    void shouldRun() {
        assertThatNoException().isThrownBy(() -> DemoApplication.main(new String[]{}));

    }

    @Test
    void shouldGetNacosConfigBean() {
        var expected = NacosConfigResource.builder()
                .serverAddr("127.0.0.1:8848")
                .prefix("PREFIX")
                .group("GROUP")
                .namespace("NAMESPACE")
                .build();

        var nacosConfigResource = client.get()
                .uri("/nacos/bean")
                .exchange()
                .expectBody(NacosConfigResource.class)
                .returnResult()
                .getResponseBody();

        assertThat(nacosConfigResource).isEqualTo(expected);

    }

}
