package dev.caobaoqi6040.backend.modules.oss.service;

import dev.caobaoqi6040.backend.modules.oss.domain.model.BucketDetails;
import dev.caobaoqi6040.backend.modules.oss.exception.BucketException;
import dev.caobaoqi6040.backend.modules.oss.exception.OSSException;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.messages.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

/**
 * BucketServiceImpl
 *
 * @author caobaoqi6040
 * @since 2025/9/21 17:16
 */
@Slf4j
@Service
public class BucketServiceImpl implements BucketService {

	private final MinioClient minioClient;

	public BucketServiceImpl(MinioClient minioClient) {
		this.minioClient = minioClient;
	}

	@Override
	public List<Bucket> listBuckets() {
		try {
			return minioClient.listBuckets();
		} catch (MinioException | GeneralSecurityException | IOException ex) {
			throw new OSSException(ex.getLocalizedMessage());
		}
	}

	@Override
	public Boolean bucketExist(String bucketName, String region) {
		try {
			return minioClient.bucketExists(BucketExistsArgs.builder()
				.bucket(bucketName)
				.region(region)
				.build());
		} catch (MinioException | GeneralSecurityException | IOException ex) {
			throw new OSSException(ex.getLocalizedMessage());
		}
	}

	@Override
	public BucketDetails getBucket(String bucketName, String region) {
		if (!bucketExist(bucketName, region)) {
			throw new BucketException(String.format("the bucket %s not exist", bucketName));
		}
		var policy = this.getBucketPolicy(bucketName, region);
		var sse = this.getSseConfiguration(bucketName, region);
		var lifecycle = this.getLifecycleConfiguration(bucketName, region);
		var notification = this.getNotificationConfiguration(bucketName, region);
		var replication = this.getReplicationConfiguration(bucketName, region);
		var versioning = this.getVersioningConfiguration(bucketName, region);
		var tags = this.getTags(bucketName, region);
		return BucketDetails.builder()
			.policy(policy)
			.sseConfiguration(sse)
			.lifecycleConfiguration(lifecycle)
			.notificationConfiguration(notification)
			.replicationConfiguration(replication)
			.versioningConfiguration(versioning)
			.tags(tags)
			.build();
	}

	@Override
	public String getBucketPolicy(String bucketName, String region) {
		try {
			return minioClient.getBucketPolicy(GetBucketPolicyArgs.builder()
				.bucket(bucketName)
				.region(region)
				.build());
		} catch (MinioException | GeneralSecurityException | IOException ex) {
			throw new OSSException(ex.getLocalizedMessage());
		}

	}

	@Override
	public SseConfiguration getSseConfiguration(String bucketName, String region) {
		try {
			return minioClient.getBucketEncryption(GetBucketEncryptionArgs.builder()
				.bucket(bucketName)
				.region(region)
				.build());
		} catch (MinioException | GeneralSecurityException | IOException ex) {
			throw new OSSException(ex.getLocalizedMessage());
		}
	}


	@Override
	public LifecycleConfiguration getLifecycleConfiguration(String bucketName, String region) {
		try {
			return minioClient.getBucketLifecycle(GetBucketLifecycleArgs.builder()
				.bucket(bucketName)
				.region(region)
				.build());
		} catch (MinioException | GeneralSecurityException | IOException ex) {
			throw new OSSException(ex.getLocalizedMessage());
		}
	}

	@Override
	public NotificationConfiguration getNotificationConfiguration(String bucketName, String region) {
		try {
			return minioClient.getBucketNotification(GetBucketNotificationArgs.builder()
				.bucket(bucketName)
				.region(region)
				.build());
		} catch (MinioException | GeneralSecurityException | IOException ex) {
			throw new OSSException(ex.getLocalizedMessage());
		}
	}

	@Override
	public ReplicationConfiguration getReplicationConfiguration(String bucketName, String region) {
		try {
			return minioClient.getBucketReplication(GetBucketReplicationArgs.builder()
				.bucket(bucketName)
				.region(region)
				.build());
		} catch (MinioException | GeneralSecurityException | IOException ex) {
			throw new OSSException(ex.getLocalizedMessage());
		}
	}

	@Override
	public VersioningConfiguration getVersioningConfiguration(String bucketName, String region) {
		try {
			return minioClient.getBucketVersioning(GetBucketVersioningArgs.builder()
				.bucket(bucketName)
				.region(region)
				.build());
		} catch (MinioException | GeneralSecurityException | IOException ex) {
			throw new OSSException(ex.getLocalizedMessage());
		}
	}

	@Override
	public Tags getTags(String bucketName, String region) {
		try {
			return minioClient.getBucketTags(GetBucketTagsArgs.builder()
				.bucket(bucketName)
				.region(region)
				.build());
		} catch (MinioException | GeneralSecurityException | IOException ex) {
			throw new OSSException(ex.getLocalizedMessage());
		}
	}


	@Override
	public void makeBucket(String bucketName, String region, Boolean objectLock) {
		if (bucketExist(bucketName, region)) {
			throw new BucketException(String.format("the bucket %s already exist with region %s", bucketName, region));
		}
		try {
			minioClient.makeBucket(MakeBucketArgs.builder()
				.bucket(bucketName)
				.region(region)
				.objectLock(objectLock)
				.build()
			);
		} catch (MinioException | GeneralSecurityException | IOException ex) {
			throw new OSSException(ex.getLocalizedMessage());
		}
	}

	@Override
	public void removeBucket(String bucketName, String region) {
		if (!bucketExist(bucketName, region)) {
			throw new BucketException(String.format("the bucket %s not exist with region %s", bucketName, region));
		}
		try {
			minioClient.removeBucket(RemoveBucketArgs.builder()
				.bucket(bucketName)
				.region(region)
				.build());
		} catch (MinioException | GeneralSecurityException | IOException ex) {
			throw new OSSException(ex.getLocalizedMessage());
		}
	}

}
