package dev.caobaoqi6040.backend.modules.oss.service;

import io.minio.Result;
import io.minio.messages.Item;
import org.springframework.web.multipart.MultipartFile;

/**
 * ObjectService
 *
 * @author caobaoqi6040
 * @since 2025/9/23 09:13
 */
public interface ObjectService {
	Boolean objectExist(String fileName, String bucketName, String region);

	String makeObject(MultipartFile file, String bucketName, String region);

	String getObjectUrl(String fileName, String bucketName, String region);

	Iterable<Result<Item>> listObject(String bucketName, String region);

	void deleteObjectUrl(String fileName, String bucketName, String region);
}
