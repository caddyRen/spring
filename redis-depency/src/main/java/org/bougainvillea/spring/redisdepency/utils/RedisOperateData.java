package org.bougainvillea.spring.redisdepency.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedisOperateData {
    private String key;
    private Object object;
    @Builder.Default
    private RedisOperates operates=RedisOperates.REDIS_STRING_OPERATE;//default String
}
