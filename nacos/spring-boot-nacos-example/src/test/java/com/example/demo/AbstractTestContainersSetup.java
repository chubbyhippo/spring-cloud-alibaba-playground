package com.example.demo;


import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.time.Duration;

@Testcontainers
public abstract class AbstractTestContainersSetup {
    @ClassRule
    @SuppressWarnings("rawtypes")
    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File("src/test/resources/compose.yaml"))
                    .withExposedService("nacos",
                            8848,
                            Wait.forListeningPort()
                                    .withStartupTimeout(Duration.ofMinutes(10)))
                    .withExposedService("nacos",
                                                9848,
                                        Wait.forListeningPort()
                                    .withStartupTimeout(Duration.ofMinutes(10)));

    @BeforeAll
    static void setUp() {
        environment.start();
    }

    @AfterAll
    static void tearDown() {
        environment.stop();
    }
}
