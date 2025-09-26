package dev.caobaoqi6040.backend.modules.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Role
 *
 * @author caobaoqi6040
 * @since 2025/9/24 10:10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(schema = "backend", value = "sys_role")
public class Role implements Serializable {
	private String id;
	@TableField("name")
	private String name;
	@TableField(exist = false)
	private List<Permission> permissions;
}
