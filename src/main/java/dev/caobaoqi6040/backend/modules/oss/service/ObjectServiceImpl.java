package dev.caobaoqi6040.backend.modules.oss.service;

import dev.caobaoqi6040.backend.modules.oss.exception.OSSException;
import dev.caobaoqi6040.backend.modules.oss.exception.ObjectException;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import io.minio.messages.Item;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * ObjectServiceImpl
 *
 * @author caobaoqi6040
 * @since 2025/9/23 09:13
 */
@Service
public class ObjectServiceImpl implements ObjectService {

	private final MinioClient minioClient;

	public ObjectServiceImpl(MinioClient minioClient) {
		this.minioClient = minioClient;
	}

	@Override
	public Boolean objectExist(String fileName, String bucketName, String region) {
		try {
			return ObjectUtils.anyNull(minioClient.getObject(GetObjectArgs.builder()
				.bucket(bucketName)
				.region(region)
				.object(fileName)
				.build())) ? Boolean.FALSE : Boolean.TRUE;
		} catch (MinioException | GeneralSecurityException | IOException ignored) {

		}
		return Boolean.FALSE;
	}

	@Override
	public String makeObject(MultipartFile file, String bucketName, String region) {
		// TODO("get user info from cache to tag the object")
		String fileName = UUID.randomUUID().toString().replace("-", "") + "-" + file.getOriginalFilename();
		if (StringUtils.isEmpty(FilenameUtils.getExtension(fileName))) {
			fileName += ".txt";
		}
		fileName = String.format("%s-%s",
			LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss", Locale.CHINA)), fileName);
		try {
			minioClient.putObject(PutObjectArgs.builder()
				.bucket(bucketName)
				.region(region)
				.object(fileName)
				.contentType(file.getContentType())
				.stream(file.getInputStream(), file.getSize(), -1)
				.build());
			return fileName;
		} catch (MinioException | GeneralSecurityException | IOException ex) {
			throw new OSSException(ex.getLocalizedMessage());
		}
	}

	@Override
	public String getObjectUrl(String fileName, String bucketName, String region) {
		try {
			if (!objectExist(fileName, bucketName, region)) {
				throw new ObjectException(String.format("the file %s not exist for bucket %s with region %s", fileName, bucketName, region));
			}
			return Optional.ofNullable(minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
				.method(Method.GET)
				.expiry(3, TimeUnit.HOURS)
				.object(fileName)
				.bucket(bucketName)
				.region(region)
				.build())).orElseThrow(() -> new ObjectException(String.format("get %s url failure", fileName)));
		} catch (MinioException | GeneralSecurityException | IOException ex) {
			throw new OSSException(ex.getLocalizedMessage());
		}
	}

	@Override
	public void deleteObjectUrl(String fileName, String bucketName, String region) {
		try {
			if (!objectExist(fileName, bucketName, region)) {
				throw new ObjectException(String.format("the file %s not exist for bucket %s with region %s", fileName, bucketName, region));
			}
			minioClient.removeObject(RemoveObjectArgs.builder()
				.object(fileName)
				.bucket(bucketName)
				.region(region)
				.build());
		} catch (MinioException | GeneralSecurityException | IOException ex) {
			throw new OSSException(ex.getLocalizedMessage());
		}
	}

	@Override
	public Iterable<Result<Item>> listObject(String bucketName, String region) {
		return minioClient.listObjects(ListObjectsArgs.builder()
			.bucket(bucketName)
			.region(region)
			.build());
	}

}
