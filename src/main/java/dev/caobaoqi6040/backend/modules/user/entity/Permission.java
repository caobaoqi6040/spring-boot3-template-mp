package dev.caobaoqi6040.backend.modules.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Permission
 *
 * @author caobaoqi6040
 * @since 2025/9/24 10:09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(schema = "backend", value = "sys_permission")
public class Permission implements Serializable {
	private String id;
	@TableField("name")
	private String name;
}
