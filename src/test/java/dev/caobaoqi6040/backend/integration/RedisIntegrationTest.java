package dev.caobaoqi6040.backend.integration;

import dev.caobaoqi6040.backend.RedisContainersConfiguration;
import dev.caobaoqi6040.backend.enums.ECache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * RedisIntegrationTest
 *
 * @author caobaoqi6040
 * @since 2025/9/24 09:42
 */
@DataRedisTest
@Import(RedisContainersConfiguration.class)
public class RedisIntegrationTest {

	private final String prefix = ECache.User.TOKEN_PREFIX.getPrefix();

	@Autowired
	StringRedisTemplate template;

	@Test
	void test1() {
		template.opsForValue().set(
			prefix + ":caobaoqi6040@gmail.com",
			"caobaoqi6040-token",
			15, TimeUnit.MINUTES
		);

		System.out.println(template.keys("user*"));
	}

	@Test
	void test2() {
		template.opsForValue().set(
			prefix + ":caobaoqi6041@gmail.com",
			"caobaoqi6041-token",
			15, TimeUnit.MINUTES
		);

		template.opsForValue().set(
			prefix + ":caobaoqi6042@gmail.com",
			"caobaoqi6042-token",
			15, TimeUnit.MINUTES
		);

		System.out.println(template.keys("user*"));
	}
}
