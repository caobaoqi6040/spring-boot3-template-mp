package dev.caobaoqi6040.backend.infrastructure.ai;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import dev.caobaoqi6040.backend.enums.Models;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.memory.repository.jdbc.MysqlChatMemoryRepositoryDialect;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * AiConfiguration
 *
 * @author caobaoqi6040
 * @since 2025/9/18 17:43
 */
@Slf4j
@Configuration
public class AiConfiguration {

	private static final String DEFAULT_PROMPT = "你是一个博学的智能聊天助手，请根据用户提问回答！";


	/**
	 * your open api
	 *
	 * @param chatModel OpenAiChatModel
	 * @return open ai chat client[gpt 4o]
	 */
	@Bean("openAI")
	public ChatClient openAI(OpenAiChatModel chatModel, ChatMemory chatMemory) {
		return ChatClient.create(chatModel).mutate()
			.defaultSystem(DEFAULT_PROMPT)
			.defaultOptions(ChatOptions.builder()
				.model(Models.Openai.GPT_4O.getModel())
				.temperature(0.7)
				.build())
			.defaultAdvisors(
				MessageChatMemoryAdvisor.builder(chatMemory).build(),
				SimpleLoggerAdvisor.builder().build())
			.build();
	}

	/**
	 * your open api
	 *
	 * @param chatModel OpenAiChatModel
	 * @return open ai chat client[gpt 4o]
	 */
	@Bean("qwen")
	public ChatClient dashScope(DashScopeChatModel chatModel, ChatMemory chatMemory) {
		return ChatClient.create(chatModel).mutate()
			.defaultSystem(DEFAULT_PROMPT)
			.defaultOptions(DashScopeChatOptions.builder()
				.withModel(Models.Qwen.QWEN3_MAX.getModel())
				.withTemperature(0.7)
				.build())
			.defaultAdvisors(
				MessageChatMemoryAdvisor.builder(chatMemory).build(),
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
	public ChatClient ollama(OllamaChatModel chatModel, ChatMemory chatMemory) {
		return ChatClient.create(chatModel).mutate()
			.defaultSystem(DEFAULT_PROMPT)
			.defaultOptions(ChatOptions.builder()
				.model(Models.Ollama.GEMMA3_4B.getModel())
				.temperature(0.7)
				.build())
			.defaultAdvisors(
				MessageChatMemoryAdvisor.builder(chatMemory).build(),
				SimpleLoggerAdvisor.builder().build())
			.build();
	}

	@Bean
	ChatMemory chatMemory(JdbcTemplate jdbcTemplate) {
		var repository = JdbcChatMemoryRepository.builder()
			.jdbcTemplate(jdbcTemplate)
			.dialect(new MysqlChatMemoryRepositoryDialect())
			.build();

		return MessageWindowChatMemory.builder()
			.chatMemoryRepository(repository)
			.maxMessages(10)
			.build();
	}

}
