package dev.caobaoqi6040.backend.mvcTest;

import dev.caobaoqi6040.backend.infrastructure.data.DataFakerConfiguration;
import dev.caobaoqi6040.backend.modules.user.controller.UserController;
import dev.caobaoqi6040.backend.modules.user.domain.UserStructImpl;
import dev.caobaoqi6040.backend.modules.user.entity.User;
import dev.caobaoqi6040.backend.modules.user.exception.UserNotFoundException;
import dev.caobaoqi6040.backend.modules.user.service.UserService;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * UserWebMvcTest
 *
 * @author caobaoqi6040
 * @see <a href="https://www.baeldung.com/spring-security-integration-tests">spring-security-integration-tests</a>
 * @since 2025/9/26 17:38
 */
@WebMvcTest(controllers = {UserController.class})
@AutoConfigureMockMvc
@Import({UserStructImpl.class, DataFakerConfiguration.class})
public class UserWebMvcTest {

	@Autowired
	MockMvc mvc;
	@Autowired
	Faker faker;
	@MockitoBean
	UserService service;

	@Test
	@WithMockUser(username = "ikun", roles = {"ADMIN"})
	void should_return_user_list_200() throws Exception {
		// given
		User user1 = User.builder()
			.username(faker.name().fullName())
			.email(faker.phoneNumber() + "@gmail.com")
			.avatar(faker.avatar().image())
			.build();
		User user2 = User.builder()
			.username(faker.name().fullName())
			.email(faker.phoneNumber() + "@gmail.com")
			.avatar(faker.avatar().image())
			.build();
		// when&then
		when(service.listUserWithDetails()).thenReturn(List.of(user1, user2));
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isNotEmpty())
			.andExpect(jsonPath("$[0].username").isString())
			.andExpect(jsonPath("$[1].username").isString());
	}

	@Test
	@WithMockUser(username = "ikun", roles = {"ADMIN"})
	void should_return_user_by_id_200() throws Exception {
		// given
		User user = User.builder()
			.id(2L)
			.username(faker.name().fullName())
			.email(faker.phoneNumber() + "@gmail.com")
			.avatar(faker.avatar().image())
			.build();
		when(service.getUserWithDetailsById(2L)).thenReturn(user);
		// when&then
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/users/{user-id}", 2L)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isNotEmpty());
	}

	@Test
	@WithMockUser(username = "ikun", roles = {"ADMIN"})
	void should_return_not_found_when_user_does_not_exist() throws Exception {
		// given
		when(service.getUserWithDetailsById(anyLong())).thenThrow(new UserNotFoundException("User not found"));
		// When & Then
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/users/{user-id}", 99L)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}

}
