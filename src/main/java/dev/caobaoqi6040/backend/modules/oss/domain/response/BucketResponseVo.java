package dev.caobaoqi6040.backend.modules.oss.domain.response;

import java.time.LocalDateTime;

public record BucketResponseVo(
	String name,
	LocalDateTime createTime
) {
}
