package dev.caobaoqi6040.backend.container;

import io.minio.MinioClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

/**
 * MinioDataInitializerConfiguration
 *
 * @author caobaoqi6040
 * @since 2025/9/28 10:55
 */
@Configuration
public class MinioDataInitializerConfiguration {

	private final MinioClient client;

	public MinioDataInitializerConfiguration(MinioClient client) {
		this.client = client;
	}
}
