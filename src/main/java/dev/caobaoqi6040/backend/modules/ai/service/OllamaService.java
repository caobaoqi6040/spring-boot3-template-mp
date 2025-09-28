package dev.caobaoqi6040.backend.modules.ai.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

public interface OllamaService {
	String sampleChat(String msg);

	Flux<String> streamChat(String msg);

	String image2SampleText(MultipartFile file);

	Flux<String> image2StreamText(MultipartFile file);
}
