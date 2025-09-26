package dev.caobaoqi6040.backend.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.caobaoqi6040.backend.modules.user.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
	List<User> listUserWithDetails();

	User getUserWithDetailsById(Long id);
}
