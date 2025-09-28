package dev.caobaoqi6040.backend.enums;

import lombok.Getter;

public class Minio {

	@Getter
	public enum Bucket {
		DEFAULT_BUCKET("spring-boot3-template-mp"),
		DEFAULT_REGIN("zh-cn");

		private final String name;

		Bucket(String name) {
			this.name = name;
		}
	}

}
