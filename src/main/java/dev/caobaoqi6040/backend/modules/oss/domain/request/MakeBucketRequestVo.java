package dev.caobaoqi6040.backend.modules.oss.domain.request;

public record MakeBucketRequestVo(
	String bucketName,
	String region,
	Boolean objectLock
) {
}
