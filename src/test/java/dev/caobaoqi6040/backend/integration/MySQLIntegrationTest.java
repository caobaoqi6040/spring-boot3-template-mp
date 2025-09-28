package dev.caobaoqi6040.backend.integration;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import dev.caobaoqi6040.backend.MySQLContainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * MySQLIntegrationTest
 *
 * @author caobaoqi6040
 * @since 2025/9/24 09:59
 */
@MybatisPlusTest(excludeAutoConfiguration = {FlywayAutoConfiguration.class})
@Import(MySQLContainersConfiguration.class)
public class MySQLIntegrationTest {

	@Autowired
	DataSource dataSource;

	@Test
	void test1() throws SQLException {
		System.out.println(dataSource.getConnection());
	}

	@Test
	void test2() throws SQLException {
		System.out.println(dataSource.getConnection());
	}

}
