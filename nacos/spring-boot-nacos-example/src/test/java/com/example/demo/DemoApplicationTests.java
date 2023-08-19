package com.example.demo;

import com.example.demo.interfaces.rest.dto.NacosConfigResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests extends AbstractTestContainersSetup {
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

    @Test
    void shouldGetNacosConfig() {
        var expected = """
                spring.cloud.nacos.config.serverAddr=127.0.0.1:8848
                spring.cloud.nacos.config.prefix=PREFIX
                spring.cloud.nacos.config.group=GROUP
                spring.cloud.nacos.config.namespace=NAMESPACE
                """.trim();

        var responseBody = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/nacos/getConfig")
                        .queryParam("dataId", "nacos-config-example.properties")
                        .queryParam("group", "DEFAULT_GROUP")
                        .build())
                .exchange()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody).isEqualTo(expected);
    }

    @Test
    void shouldPublishConfig() {

        var responseBody = client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/nacos/getConfig")
                        .queryParam("dataId", "nacos-config-example-2.properties")
                        .queryParam("group", "NEW_GROUP")
                        .queryParam("content", """
                                   spring.cloud.nacos.config.serverAddr=127.0.0.1:8848
                                   spring.cloud.nacos.config.prefix=PREFIX
                                   spring.cloud.nacos.config.group=GROUP
                                   spring.cloud.nacos.config.namespace=NAMESPACE
                                """.trim())
                        .build())
                .exchange()
                .expectBody(Boolean.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody).isTrue();
    }

}
