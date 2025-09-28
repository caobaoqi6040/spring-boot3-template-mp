package dev.caobaoqi6040.backend.modules.oss.domain.model;

import io.minio.messages.*;
import lombok.Builder;
import lombok.Data;

/**
 * BucketDetails
 *
 * @author caobaoqi6040
 * @since 2025/9/22 13:01
 */
@Data
@Builder
// TODO("获取嵌套信息[当前只有 policy 可以正常返回]")
public class BucketDetails {
	private String policy;
	private SseConfiguration sseConfiguration;
	private LifecycleConfiguration lifecycleConfiguration;
	private NotificationConfiguration notificationConfiguration;
	private ReplicationConfiguration replicationConfiguration;
	private VersioningConfiguration versioningConfiguration;
	private Tags tags;
}
