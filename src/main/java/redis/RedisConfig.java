package redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by dable on 2017/5/17.
 */
@Configuration
public class RedisConfig {


  @Bean
  public RedisTemplate setRedisTemplate(@Autowired RedisTemplate redisTemplate) {
    redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
    redisTemplate.setValueSerializer(StringRedisSerializer.UTF_8);
    redisTemplate.setHashKeySerializer(StringRedisSerializer.UTF_8);
    redisTemplate.setHashValueSerializer(StringRedisSerializer.UTF_8);
    return redisTemplate;
  }

}