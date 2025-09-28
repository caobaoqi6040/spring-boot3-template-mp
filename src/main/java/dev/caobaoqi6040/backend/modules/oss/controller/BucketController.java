package dev.caobaoqi6040.backend.modules.oss.controller;

import dev.caobaoqi6040.backend.modules.oss.domain.BucketStruct;
import dev.caobaoqi6040.backend.modules.oss.domain.request.MakeBucketRequestVo;
import dev.caobaoqi6040.backend.modules.oss.domain.response.BucketDetailsResponseVo;
import dev.caobaoqi6040.backend.modules.oss.domain.response.BucketResponseVo;
import dev.caobaoqi6040.backend.modules.oss.exception.BucketException;
import dev.caobaoqi6040.backend.modules.oss.exception.OSSException;
import dev.caobaoqi6040.backend.modules.oss.service.BucketService;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * BucketController
 *
 * @author caobaoqi6040
 * @since 2025/9/21 19:55
 */
@Slf4j
@RestController
@RequestMapping("api/v1/oss/bucket")
public class BucketController {

	private final BucketService service;
	private final BucketStruct struct;

	public BucketController(BucketService service, BucketStruct struct) {
		this.service = service;
		this.struct = struct;
	}

	@GetMapping
	public ResponseEntity<List<BucketResponseVo>> listBuckets() {
		List<Bucket> buckets = service.listBuckets();
		var vos = buckets.stream().map(struct::bucket2BucketResponseVo).toList();
		return ResponseEntity.ok(vos);
	}

	@GetMapping("/{bucket-name}/{region}")
	public ResponseEntity<BucketDetailsResponseVo> getBucket(
		@PathVariable("bucket-name") String bucketName,
		@PathVariable("region") String region) {
		var bucket = service.getBucket(bucketName, region);
		return ResponseEntity.ok(struct.bucketDetails2BucketDetailsResponseVo(bucket));
	}

	@PostMapping
	public ResponseEntity<Void> makeBucket(@RequestBody MakeBucketRequestVo vo) {
		service.makeBucket(vo.bucketName(), vo.region(), vo.objectLock());
		return ResponseEntity.created(URI.create(
			String.format("api/v1/oss/bucket/%s/%s", vo.bucketName(), vo.region())
		)).build();
	}

	@DeleteMapping("/{bucket-name}/{region}")
	public ResponseEntity<Void> removeBucket(
		@PathVariable("bucket-name") String bucketName,
		@PathVariable("region") String region) {
		service.removeBucket(bucketName, region);
		return ResponseEntity.accepted().build();
	}

	@ExceptionHandler({BucketException.class})
	public ResponseEntity<String> handleOSSException(OSSException ex) {
		log.warn("[bucket] exception msg --> {}", ex.getLocalizedMessage(), ex);
		return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}

}
