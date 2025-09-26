package dev.caobaoqi6040.backend.modules.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.caobaoqi6040.backend.modules.user.entity.User;
import dev.caobaoqi6040.backend.modules.user.exception.UserNotFoundException;
import dev.caobaoqi6040.backend.modules.user.mapper.UserMapper;
import dev.caobaoqi6040.backend.modules.user.service.RoleService;
import dev.caobaoqi6040.backend.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserServiceImpl
 *
 * @author caobaoqi6040
 * @since 2025/9/24 10:56
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	private final RoleService roleService;

	@Override
	public List<User> listUserWithDetails() {
		List<User> users = this.list();
		users.forEach(roleService::loadRole);
		return users;
	}

	@Override
	public User getUserWithDetailsById(Long id) {
		User user = this.getOneOpt(Wrappers.lambdaQuery(User.class)
				.eq(User::getId, id))
			.orElseThrow(() -> new UserNotFoundException(String.format("user not found with userId %s", id)));
		roleService.loadRole(user);
		return user;
	}
}
