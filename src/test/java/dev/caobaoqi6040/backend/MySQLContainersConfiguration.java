package dev.caobaoqi6040.backend;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class MySQLContainersConfiguration {

	private static final String MySQL_IMAGE = "mysql:8.0.42";

	@Bean
	@ServiceConnection
	MySQLContainer<?> redisContainer() {
		return new MySQLContainer<>(DockerImageName.parse(MySQL_IMAGE));
	}

}
