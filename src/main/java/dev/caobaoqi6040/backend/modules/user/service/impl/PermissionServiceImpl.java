package dev.caobaoqi6040.backend.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.caobaoqi6040.backend.modules.user.entity.Permission;
import dev.caobaoqi6040.backend.modules.user.entity.Role;
import dev.caobaoqi6040.backend.modules.user.exception.PermissionNotFoundException;
import dev.caobaoqi6040.backend.modules.user.repository.PermissionMapper;
import dev.caobaoqi6040.backend.modules.user.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * permissionServiceImpl
 *
 * @author caobaoqi6040
 * @since 2025/9/24 10:54
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

	private final PermissionMapper mapper;

	@Override
	public void loadPermission(Role role) {
		List<Permission> permissions = Optional.ofNullable(mapper.findByRoleId(role.getId()))
			.orElseThrow(() -> new PermissionNotFoundException(String.format("permission not found with roleId %s", role.getId())));
		role.setPermissions(permissions);
	}
}
