package dev.caobaoqi6040.backend.infrastructure.data;

import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;
import java.util.Random;

/**
 * DataFakerConfiguration
 *
 * @author caobaoqi6040
 * @since 2025/9/26 17:46
 */
@Configuration
public class DataFakerConfiguration {

	@Bean
	Faker faker() {
		return new Faker(Locale.CHINA, new Random(1L));
	}

}
