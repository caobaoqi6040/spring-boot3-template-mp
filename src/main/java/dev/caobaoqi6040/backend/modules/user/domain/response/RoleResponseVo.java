package dev.caobaoqi6040.backend.modules.user.domain.response;

import java.util.List;

public record RoleResponseVo(
	String name,
	List<PermissionResponseVo> permissions
) {
}
