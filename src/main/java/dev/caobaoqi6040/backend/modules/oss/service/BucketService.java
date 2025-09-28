package dev.caobaoqi6040.backend.modules.oss.service;

import dev.caobaoqi6040.backend.modules.oss.domain.model.BucketDetails;
import io.minio.messages.*;

import java.util.List;

public interface BucketService {
	List<Bucket> listBuckets();

	Boolean bucketExist(String bucketName, String region);

	BucketDetails getBucket(String bucketName, String region);

	String getBucketPolicy(String bucketName, String region);

	SseConfiguration getSseConfiguration(String bucketName, String region);

	LifecycleConfiguration getLifecycleConfiguration(String bucketName, String region);

	NotificationConfiguration getNotificationConfiguration(String bucketName, String region);

	ReplicationConfiguration getReplicationConfiguration(String bucketName, String region);

	VersioningConfiguration getVersioningConfiguration(String bucketName, String region);

	Tags getTags(String bucketName, String region);

	void makeBucket(String bucketName, String region, Boolean objectLock);

	void removeBucket(String bucketName, String region);
}
