package dev.caobaoqi6040.backend.modules.ai.service;

import reactor.core.publisher.Flux;

import java.net.MalformedURLException;

public interface OllamaService {
	String sampleChat(String msg);

	Flux<String> streamChat(String msg);

	String image2SampleText(String url) throws MalformedURLException;

	Flux<String> image2StreamText(String url) throws MalformedURLException;
}
