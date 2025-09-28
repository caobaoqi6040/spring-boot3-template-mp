package dev.caobaoqi6040.backend.modules.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import reactor.core.publisher.Flux;

import java.net.MalformedURLException;
import java.util.UUID;

/**
 * OllamaServiceImpl
 *
 * @author caobaoqi6040
 * @since 2025/9/28 09:44
 */
@Service
// TODO("spring security 下应用是无状态的也就是 session 用不了了")
@SessionScope
public class OllamaServiceImpl implements OllamaService {

	private final ChatClient chatClient;
	private final String conversationId;

	public OllamaServiceImpl(@Qualifier("ollama") ChatClient chatClient) {
		this.chatClient = chatClient;
		this.conversationId = UUID.randomUUID().toString();
	}

	@Override
	public String sampleChat(String msg) {
		return chatClient
			.prompt()
			.user(spec -> spec.text(msg))
			.advisors(spec -> spec.param(ChatMemory.CONVERSATION_ID, conversationId))
			.call()
			.content();
	}

	@Override
	public String image2SampleText(String url) throws MalformedURLException {
		UrlResource resource = new UrlResource(url);
		return chatClient
			.prompt()
			.user(spec -> spec
				.text("描述图片中的内容")
				.media(MediaType.IMAGE_JPEG, resource))
			.advisors(spec -> spec.param(ChatMemory.CONVERSATION_ID, conversationId))
			.call()
			.content();
	}

	@Override
	public Flux<String> streamChat(String msg) {
		return chatClient
			.prompt()
			.user(spec -> spec.text(msg))
			.advisors(spec -> spec.param(ChatMemory.CONVERSATION_ID, conversationId))
			.stream()
			.content();
	}

	@Override
	public Flux<String> image2StreamText(String url) throws MalformedURLException {
		UrlResource resource = new UrlResource(url);
		return chatClient
			.prompt()
			.user(spec -> spec
				.text("描述图片中的内容")
				.media(MediaType.IMAGE_JPEG, resource))
			.advisors(spec -> spec.param(ChatMemory.CONVERSATION_ID, conversationId))
			.stream()
			.content();
	}

}
