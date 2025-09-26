package dev.caobaoqi6040.backend.modules.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User
 *
 * @author caobaoqi6040
 * @since 2025/9/24 10:10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(schema = "backend", value = "sys_user")
public class User implements Serializable {
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;
	@TableField("username")
	private String username;
	@TableField("email")
	private String email;
	@TableField("password")
	private String password;
	@TableField("avatar")
	private String avatar;
	@TableField(value = "create_time",fill = FieldFill.INSERT)
	private LocalDateTime createTime;
	@TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;
	@TableLogic
	private Boolean enable;
	@TableField(exist = false)
	private List<Role> roles;
}
