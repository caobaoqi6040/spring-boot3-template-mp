package dev.caobaoqi6040.backend;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MinIOContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * MinioContainersConfiguration
 *
 * @author caobaoqi6040
 * @since 2025/9/28 10:45
 */
@TestConfiguration(proxyBeanMethods = false)
public class MinioContainersConfiguration {

	private static final String MINIO_IMAGE = "minio/minio:RELEASE.2024-08-03T04-33-23Z.fips";

	@Bean
	@ServiceConnection
	MinIOContainer redisContainer() {
		return new MinIOContainer(DockerImageName.parse(MINIO_IMAGE));
	}

}
