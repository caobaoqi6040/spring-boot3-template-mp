package dev.caobaoqi6040.backend.modules.user.controller;

import dev.caobaoqi6040.backend.modules.user.domain.UserStruct;
import dev.caobaoqi6040.backend.modules.user.domain.response.UserResponseVo;
import dev.caobaoqi6040.backend.modules.user.entity.User;
import dev.caobaoqi6040.backend.modules.user.exception.PermissionNotFoundException;
import dev.caobaoqi6040.backend.modules.user.exception.RoleNotFoundException;
import dev.caobaoqi6040.backend.modules.user.exception.UserNotFoundException;
import dev.caobaoqi6040.backend.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController
 *
 * @author caobaoqi6040
 * @since 2025/9/24 10:58
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService service;
	private final UserStruct struct;

	@GetMapping
	public ResponseEntity<List<UserResponseVo>> list() {
		List<User> users = service.listUserWithDetails();
		var vos = users.stream().map(struct::user2UserResponse).toList();
		return ResponseEntity.ok(vos);
	}

	@GetMapping("/{user-id}")
	public ResponseEntity<UserResponseVo> item(@PathVariable("user-id") Long userId) {
		User user = service.getUserWithDetailsById(userId);
		return ResponseEntity.ok(struct.user2UserResponse(user));
	}

	@ExceptionHandler({RoleNotFoundException.class, PermissionNotFoundException.class, UserNotFoundException.class})
	public ResponseEntity<Void> handlerUserException(Exception ex) {
		log.warn("[role | permission | user exception] msg -> {}", ex.getLocalizedMessage());
		return ResponseEntity.notFound().build();
	}

}
