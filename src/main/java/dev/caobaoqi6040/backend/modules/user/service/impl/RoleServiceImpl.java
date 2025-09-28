package dev.caobaoqi6040.backend.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.caobaoqi6040.backend.modules.user.entity.Role;
import dev.caobaoqi6040.backend.modules.user.entity.User;
import dev.caobaoqi6040.backend.modules.user.exception.PermissionNotFoundException;
import dev.caobaoqi6040.backend.modules.user.repository.RoleMapper;
import dev.caobaoqi6040.backend.modules.user.service.PermissionService;
import dev.caobaoqi6040.backend.modules.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * RoleServiceImpl
 *
 * @author caobaoqi6040
 * @since 2025/9/24 10:55
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	private final RoleMapper mapper;
	private final PermissionService permissionService;

	@Override
	public void loadRole(User user) {
		List<Role> roles = Optional.ofNullable(mapper.findByUserId(user.getId()))
			.orElseThrow(() -> new PermissionNotFoundException(String.format("role not found with userId %s", user.getId())));
		roles.forEach(permissionService::loadPermission);
		user.setRoles(roles);
	}
}
