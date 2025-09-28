package dev.caobaoqi6040.backend.infrastructure.ai;

import dev.caobaoqi6040.backend.enums.Models;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AiConfiguration
 *
 * @author caobaoqi6040
 * @since 2025/9/18 17:43
 */
@Slf4j
@Configuration
public class AiConfiguration {

	/**
	 * your open api
	 *
	 * @param chatModel OpenAiChatModel
	 * @return open ai chat client[gpt 4o]
	 */
	@Bean("open-ai")
	public ChatClient openAI(OpenAiChatModel chatModel) {
		return ChatClient.create(chatModel).mutate()
			.defaultSystem("you are a helpful assistant")
			.defaultOptions(ChatOptions.builder()
				.model(Models.Openai.GPT_4O.getModel()).build())
			.defaultAdvisors(
				SimpleLoggerAdvisor.builder().build())
			.build();
	}


	/**
	 * ollama
	 *
	 * @param chatModel OllamaChatModel
	 * @return ollama chat client[gemma3 4b]
	 */
	@Bean("ollama")
	public ChatClient ollama(OllamaChatModel chatModel) {
		return ChatClient.create(chatModel).mutate()
			.defaultSystem("you are a helpful assistant")
			.defaultOptions(ChatOptions.builder()
				.model(Models.Ollama.GEMMA3_4B.getModel()).build())
			.defaultAdvisors(
				SimpleLoggerAdvisor.builder().build())
			.build();
	}
}
