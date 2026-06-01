package com.byc.common.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Auto-configuration for Redis with customized JSON serialization.
 *
 * <p>Configures a {@link RedisTemplate} with String key serializer and Jackson-based
 * value serializer that supports polymorphic type handling.
 */
@AutoConfiguration
@ConditionalOnClass(RedisTemplate.class)
public class RedisAutoConfiguration {

  /**
   * Creates a RedisTemplate bean with custom serialization configuration.
   *
   * <p>The template uses {@link StringRedisSerializer} for keys and hash keys,
   * and {@link GenericJackson2JsonRedisSerializer} for values and hash values
   * to support JSON serialization with polymorphic type information.
   *
   * @param cf the Redis connection factory
   * @return a configured RedisTemplate instance with String keys and Object values
   */
  @Bean
  @ConditionalOnMissingBean(name = "redisTemplate")
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory cf) {
    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    om.activateDefaultTyping(BasicPolymorphicTypeValidator.builder().allowIfSubType(Object.class).build(),
        ObjectMapper.DefaultTyping.NON_FINAL);

    GenericJackson2JsonRedisSerializer jacksonSer = new GenericJackson2JsonRedisSerializer(om);
    StringRedisSerializer stringSer = new StringRedisSerializer();

    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(cf);
    template.setKeySerializer(stringSer);
    template.setHashKeySerializer(stringSer);
    template.setValueSerializer(jacksonSer);
    template.setHashValueSerializer(jacksonSer);
    template.afterPropertiesSet();
    return template;
  }
}
