package dev.caobaoqi6040.backend.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.caobaoqi6040.backend.modules.user.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

	@Select("""
			SELECT
				sr.id AS id,
				sr.name AS name
			FROM sys_role sr
				INNER JOIN backend.sys_user_role_map surm on sr.id = surm.role_id
			WHERE user_id=#{userId}
		""")
	List<Role> findByUserId(@Param("userId") Long userId);

}
