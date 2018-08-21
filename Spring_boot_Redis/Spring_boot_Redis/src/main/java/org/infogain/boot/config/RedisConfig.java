package org.infogain.boot.config;

import org.infogain.boot.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

	@Bean
	JedisConnectionFactory jedisConnectionFactory()
	{
		return new JedisConnectionFactory();
	}
	
	@Bean
	RedisTemplate<String, String> redisTemplate()
	{
		RedisTemplate<String, String> redisTemplateObject=new RedisTemplate<>();
		redisTemplateObject.setConnectionFactory(jedisConnectionFactory());
		return redisTemplateObject;
	}

}
