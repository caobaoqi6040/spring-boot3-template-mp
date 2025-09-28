package dev.caobaoqi6040.backend.modules.oss.controller;

import dev.caobaoqi6040.backend.modules.oss.exception.OSSException;
import dev.caobaoqi6040.backend.modules.oss.exception.ObjectException;
import dev.caobaoqi6040.backend.modules.oss.service.ObjectService;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * ObjectController
 *
 * @author caobaoqi6040
 * @since 2025/9/23 09:14
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/oss/file")
public class ObjectController {

	private final ObjectService service;

	public ObjectController(ObjectService service) {
		this.service = service;
	}

	@GetMapping("/{bucket-name}/{region}")
	public ResponseEntity<List<String>> list(
		@PathVariable("bucket-name") String bucketName,
		@PathVariable("region") String region
	) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
		List<String> data = new ArrayList<>();
		Iterable<Result<Item>> results = service.listObject(bucketName, region);
		for (Result<Item> result : results) {
			data.add(result.get().objectName());
		}
		return ResponseEntity.ok(data);
	}

	@PostMapping("/{bucket-name}/{region}")
	public ResponseEntity<Void> upload(@RequestBody MultipartFile file,
									   @PathVariable("bucket-name") String bucketName,
									   @PathVariable("region") String region) {
		String fileName = service.makeObject(file, bucketName, region);
		return ResponseEntity.created(URI.create(
			String.format("api/v1/oss/file/%s/%s/%s", bucketName, region, fileName)
		)).build();
	}

	@GetMapping("/{bucket-name}/{region}/{file-name}")
	public ResponseEntity<String> getUrl(
		@PathVariable("bucket-name") String bucketName,
		@PathVariable("region") String region,
		@PathVariable("file-name") String fileName) {
		String url = service.getObjectUrl(fileName, bucketName, region);
		return ResponseEntity.ok(url);
	}

	@DeleteMapping("/{bucket-name}/{region}/{file-name}")
	public ResponseEntity<Void> delete(
		@PathVariable("bucket-name") String bucketName,
		@PathVariable("region") String region,
		@PathVariable("file-name") String fileName) {
		service.deleteObjectUrl(fileName, bucketName, region);
		return ResponseEntity.accepted().build();
	}

	@ExceptionHandler({ObjectException.class})
	public ResponseEntity<String> handleOSSException(OSSException ex) {
		log.warn("[object] exception msg --> {}", ex.getLocalizedMessage(), ex);
		return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}

}
