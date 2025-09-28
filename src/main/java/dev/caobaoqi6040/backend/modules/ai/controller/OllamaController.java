package dev.caobaoqi6040.backend.modules.ai.controller;

import dev.caobaoqi6040.backend.modules.ai.domain.request.ChatRequestVo;
import dev.caobaoqi6040.backend.modules.ai.service.OllamaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.net.MalformedURLException;

/**
 * OllamaController
 *
 * @author caobaoqi6040
 * @since 2025/9/18 17:42
 */
@RestController
@RequestMapping("/api/v1/ai/ollama")
public class OllamaController {

	private final OllamaService service;

	public OllamaController(OllamaService service) {
		this.service = service;
	}

	@PostMapping("/sample-chat")
	public ResponseEntity<String> sampleChat(@RequestBody ChatRequestVo vo) {
		String result = service.sampleChat(vo.msg());
		return ResponseEntity.ok(result);
	}

	@PostMapping("/stream-chat")
	public ResponseEntity<Flux<String>> streamChat(@RequestBody ChatRequestVo vo) {
		Flux<String> flux = service.streamChat(vo.msg());
		return ResponseEntity.ok(flux);
	}

	@PostMapping("/image2text")
	public ResponseEntity<String> image2text(@RequestBody ChatRequestVo vo) throws MalformedURLException {
		String result = service.image2SampleText(vo.url());
		return ResponseEntity.ok(result);
	}

}
