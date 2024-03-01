package com.xiang.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class MyRedisTemplate {
@Bean
    @SuppressWarnings("all")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
{
  RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
  redisTemplate.setConnectionFactory(redisConnectionFactory);

  //json
    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
    ObjectMapper om=new ObjectMapper();
    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    jackson2JsonRedisSerializer.setObjectMapper(om);
    //String
    StringRedisSerializer stringSerializer=new  StringRedisSerializer();

    //key采用String
    redisTemplate.setKeySerializer(stringSerializer);
    //hash的key采用String
    redisTemplate.setHashKeySerializer(stringSerializer);
    //value采用jackson
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    //hash的value采用jackson
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
}

}
