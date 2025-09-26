package dev.caobaoqi6040.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	void contextLoad() {

	}

	@Test
	void generate_test_pwd() {
		System.out.println(passwordEncoder.encode("pwd-admin"));
		System.out.println(passwordEncoder.encode("pwd-general"));
	}

}
