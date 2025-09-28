package dev.caobaoqi6040.backend.modules.oss.domain.request;

public record MinioRequestVo(
	String bucketName,
	String region
) {
}
