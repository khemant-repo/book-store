package com.sivalabs.bookstore.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication
				.from(CatalogServiceApplication::main)
				.with(TestcontainersConfiguration.class)
				.run(args);
	}

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgreContainer(){
		return new PostgreSQLContainer<>(
				DockerImageName.parse("postgres:16-alpine")
		);
	}
}
