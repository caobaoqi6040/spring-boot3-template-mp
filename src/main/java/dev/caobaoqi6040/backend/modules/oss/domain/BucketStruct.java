package dev.caobaoqi6040.backend.modules.oss.domain;

import dev.caobaoqi6040.backend.modules.oss.domain.model.BucketDetails;
import dev.caobaoqi6040.backend.modules.oss.domain.response.BucketDetailsResponseVo;
import dev.caobaoqi6040.backend.modules.oss.domain.response.BucketResponseVo;
import io.minio.messages.Bucket;
import org.mapstruct.*;

/**
 * bucket mapper struct
 *
 * @see BucketResponseVo
 * @see BucketDetailsResponseVo
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BucketStruct {
	@Mappings({
		@Mapping(target = "name", expression = "java(bucket.name())"),
		@Mapping(target = "createTime", expression = "java(bucket.creationDate().toLocalDateTime())"),
	})
	BucketResponseVo bucket2BucketResponseVo(Bucket bucket);

	BucketDetailsResponseVo bucketDetails2BucketDetailsResponseVo(BucketDetails details);
}
