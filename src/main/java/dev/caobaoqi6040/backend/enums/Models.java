package dev.caobaoqi6040.backend.enums;

import lombok.Getter;

/**
 * Models
 *
 * @author caobaoqi6040
 * @since 2025/9/19 11:43
 */
public class Models {

	@Getter
	public enum Qwen {
		QWEN3_MAX("qwen3-max"),
		TEXT_EMBEDDING_V4("text-embedding-v4");

		private final String model;

		Qwen(String model) {
			this.model = model;
		}

	}

	@Getter
	public enum DeepSeek {
		DEEPSEEK_CHAT("deepseek-chat"),
		DEEPSEEK_REASONER("deepseek-reasoner");

		private final String model;

		DeepSeek(String model) {
			this.model = model;
		}

	}

	@Getter
	public enum Openai {
		GPT_4O("gpt-4o");

		private final String model;

		Openai(String model) {
			this.model = model;
		}

	}

	@Getter
	public enum Ollama {
		QWEN3_32B("qwen3:32b"),
		GEMMA3_4B("gemma3:4b");

		private final String model;

		Ollama(String model) {
			this.model = model;
		}
	}
}
