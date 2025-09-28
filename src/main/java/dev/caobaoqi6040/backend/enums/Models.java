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
		GPT_4_5_PREVIEW("gpt-4.5-preview"),
		GPT_4O("gpt-4o"),
		CHATGPT_4O_LATEST("chatgpt-4o-latest"),
		GPT_4O_MINI("gpt-4o-mini"),
		O1("o1"),
		O1_MINI("o1-mini"),
		O1_PREVIEW("o1-preview"),
		O3_MINI("o3-mini"),
		GPT_4O_REALTIME_PREVIEW("gpt-4o-realtime-preview"),
		GPT_4O_MINI_REALTIME_PREVIEW("gpt-4o-mini-realtime-preview"),
		GPT_4O_AUDIO_PREVIEW("gpt-4o-audio-preview"),
		GPT_4O_MINI_AUDIO_PREVIEW("gpt-4o-mini-audio-preview"),
		GPT_4_TURBO("gpt-4-turbo"),
		GPT_4_TURBO_PREVIEW("gpt-4-turbo-preview"),
		GPT_4("gpt-4"),
		GPT_3_5_TURBO_0125("gpt-3.5-turbo-0125"),
		GPT_3_5_TURBO("gpt-3.5-turbo"),
		GPT_3_5_TURBO_1106("gpt-3.5-turbo-1106"),
		GPT_3_5_TURBO_INSTRUCT("gpt-3.5-turbo-instruct"),
		DALL_E_3("dall-e-3"),
		DALL_E_2("dall-e-2"),
		TTS_1("tts-1"),
		TTS_1_HD("tts-1-hd"),
		WHISPER_1("whisper-1"),
		TEXT_EMBEDDING_3_LARGE("text-embedding-3-large"),
		TEXT_EMBEDDING_3_SMALL("text-embedding-3-small"),
		TEXT_EMBEDDING_ADA_002("text-embedding-ada-002"),
		OMNI_MODERATION_LATEST("omni-moderation-latest"),
		OMNI_MODERATION_2024_09_26("omni-moderation-2024-09-26"),
		TEXT_MODERATION_LATEST("text-moderation-latest"),
		TEXT_MODERATION_STABLE("text-moderation-stable"),
		TEXT_MODERATION_007("text-moderation-007"),
		BABBAGE_002("babbage-002"),
		DAVINCI_002("davinci-002");

		private final String model;

		Openai(String model) {
			this.model = model;
		}

	}

	@Getter
	public enum Grok {
		GROK_3_BETA_LATEST("grok-3-beta"),
		GROK_3_BETA("grok-3-beta"),
		GROK_3("grok-3-beta"),
		GROK_3_MINI_FAST_LATEST("grok-3-mini-fast-beta"),
		GROK_3_MINI_FAST_BETA("grok-3-mini-fast-beta"),
		GROK_3_MINI_FAST("grok-3-mini-fast-beta"),
		GROK_3_FAST_LATEST("grok-3-fast-beta"),
		GROK_3_FAST_BETA("grok-3-fast-beta"),
		GROK_3_FAST("grok-3-fast-beta"),
		GROK_3_MINI_LATEST("grok-3-mini-beta"),
		GROK_3_MINI_BETA("grok-3-mini-beta"),
		GROK_3_MINI("grok-3-mini-beta"),
		GROK_2_IMAGE_LATEST("grok-2-image-1212"),
		GROK_2_IMAGE("grok-2-image-1212"),
		GROK_2_IMAGE_1212("grok-2-image-1212"),
		grok_2_latest("grok-2-1212"),
		GROK_2("grok-2-1212"),
		GROK_2_1212("grok-2-1212"),
		GROK_2_VISION_1212("grok-2-vision-1212"),
		GROK_BETA("grok-beta"),
		GROK_VISION_BETA("grok-vision-beta");

		private final String model;

		Grok(String model) {
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
