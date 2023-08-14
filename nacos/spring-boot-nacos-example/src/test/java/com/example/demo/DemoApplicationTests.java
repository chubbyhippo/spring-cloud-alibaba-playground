package com.example.demo;

import com.example.demo.interfaces.rest.dto.NacosConfigResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
	@Autowired
	private WebTestClient client;
	@Test
	void shouldRun() {
		assertThatNoException().isThrownBy(() -> DemoApplication.main(new String[]{}));

	}

	@Test
	void shouldGetNacosConfig() {
		client.get()
				.uri("")
				.exchange()
				.expectBody(NacosConfigResource.class);
	}

}
