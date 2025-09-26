package dev.caobaoqi6040.backend.modules.user.domain.response;

import java.util.List;

public record UserResponseVo(
	String username,
	String email,
	String avatar,
	List<RoleResponseVo> roles
) {
}
