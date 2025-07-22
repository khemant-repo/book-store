package com.sivalabs.bookstore.catalog;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

/**
 * 1. RANDOM_PORT: it will random pick any available port and bind it during run time to avoid port conflict.
 * 2. @LocalServerPort : will bind randomly allocated  port variable
 * 3.
 *
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(TestContainersConfiguration.class)
public abstract class AbstractIT {

    @LocalServerPort
    int port;

    @BeforeEach
    void setup() {
        RestAssured.port =
                port; // will bind randomly assigned port to running application instead of picking port defined in
        // application.properties file
    }
}
