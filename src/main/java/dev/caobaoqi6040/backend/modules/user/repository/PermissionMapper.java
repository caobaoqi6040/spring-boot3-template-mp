package dev.caobaoqi6040.backend.modules.user.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.caobaoqi6040.backend.modules.user.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

	@Select("""
			SELECT
				sp.id AS id,
				sp.name AS name
			FROM sys_permission AS sp
				INNER JOIN backend.sys_role_permission_map srpm on sp.id = srpm.permission_id
			WHERE role_id=${roleId}
		""")
	List<Permission> findByRoleId(@Param("roleId") String roleId);
}
