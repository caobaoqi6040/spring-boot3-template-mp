package dev.caobaoqi6040.backend.infrastructure.data;

import io.minio.MinioClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MinioConfiguration
 *
 * @author caobaoqi6040
 * @since 2025/9/21 17:13
 */
@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfiguration {

	private String endpoint;
	private String accessKey;
	private String secretKey;

	@Bean
	MinioClient minioClient() {
		return MinioClient.builder()
			.endpoint(endpoint)
			.credentials(accessKey, secretKey)
			.build();
	}

}
