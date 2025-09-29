package dev.caobaoqi6040.backend.modules.ai.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;

/**
 * QwenController
 *
 * @author caobaoqi6040
 * @since 2025/9/29 14:17
 */
@RestController
@RequestMapping("/api/v1/ai/qwen")
public class QwenController {

	private final ChatClient client;

	public QwenController(ChatClient qwen) {
		this.client = qwen;
	}

	@GetMapping("/sample-chat")
	public String sampleChat(@RequestParam(value = "query") String query) {
		return client.prompt()
			.user(spec -> spec.text(query))
			.call()
			.content();
	}

	@GetMapping("/stream-chat")
	public Flux<String> streamChat(@RequestParam(value = "query") String query, HttpServletResponse response) {
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		return client.prompt()
			.user(spec -> spec.text(query))
			.stream()
			.content();
	}

}
