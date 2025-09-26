package dev.caobaoqi6040.backend.modules.user.domain;

import dev.caobaoqi6040.backend.modules.user.domain.response.PermissionResponseVo;
import dev.caobaoqi6040.backend.modules.user.domain.response.RoleResponseVo;
import dev.caobaoqi6040.backend.modules.user.domain.response.UserResponseVo;
import dev.caobaoqi6040.backend.modules.user.entity.Permission;
import dev.caobaoqi6040.backend.modules.user.entity.Role;
import dev.caobaoqi6040.backend.modules.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserStruct {
	PermissionResponseVo permission2PermissionResponseVo(Permission permission);
	RoleResponseVo role2RoleResponseVo(Role role);
	UserResponseVo user2UserResponse(User user);
}
