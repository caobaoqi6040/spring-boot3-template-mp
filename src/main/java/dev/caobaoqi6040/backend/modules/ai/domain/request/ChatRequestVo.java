package dev.caobaoqi6040.backend.modules.ai.domain.request;

import org.springframework.core.io.Resource;

public record ChatRequestVo(
	String msg,
	Resource resource
) {
}
