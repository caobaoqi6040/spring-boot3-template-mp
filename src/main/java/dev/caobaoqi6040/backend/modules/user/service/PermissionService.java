package dev.caobaoqi6040.backend.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.caobaoqi6040.backend.modules.user.entity.Permission;
import dev.caobaoqi6040.backend.modules.user.entity.Role;

public interface PermissionService extends IService<Permission> {
	void loadPermission(Role role);
}
