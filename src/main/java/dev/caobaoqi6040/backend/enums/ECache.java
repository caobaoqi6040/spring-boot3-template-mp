package dev.caobaoqi6040.backend.enums;

import lombok.Getter;

/**
 * CacheEnum
 *
 * @author caobaoqi6040
 * @since 2025/9/24 09:14
 */
public class ECache {

	@Getter
	public enum User {

		TOKEN_PREFIX("user::auth::token"),
		EMAIL_VALIDATOR_PREFIX("user::auth::email::validator");

		private final String prefix;

		User(String prefix) {
			this.prefix = prefix;
		}
	}

}
