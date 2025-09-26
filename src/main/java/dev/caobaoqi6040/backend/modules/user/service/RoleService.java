package dev.caobaoqi6040.backend.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.caobaoqi6040.backend.modules.user.entity.Role;
import dev.caobaoqi6040.backend.modules.user.entity.User;

public interface RoleService extends IService<Role> {
	void loadRole(User user);
}
