package com.workjo.pointapp.config;


import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.time.Duration;


@Slf4j
@Configuration
@EnableRedisRepositories(basePackages = "com.workjo.pointapp")
public class RedisConfig {

	@Value("${spring.data.redis.port}")
	public int port;

	@Value("${spring.data.redis.host}")
	public String host;

	@Value("${spring.data.redis.password}")
	public String password;

	@Value("${spring.data.redis.connect-timeout}")
	public Long timeout;


	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
			.commandTimeout(Duration.ofMillis(timeout))
			.build();
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
		redisStandaloneConfiguration.setPassword(password);
		return new LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration);
	}


	@Bean
	public RedisTemplate<?, ?> redisTemplate() {
		RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}


	@Bean
	public RedisCommands<String, String> redisCommands() {
		log.info(host);
		StatefulRedisConnection<String, String> connection = RedisClient.create(RedisURI.Builder.redis(host, port).withPassword(password.toCharArray()).withDatabase(0).build()).connect();
		//		StatefulRedisConnection<String, String> connection = RedisClient.create(RedisURI.Builder.redis(host, port).withDatabase(0).build()).connect();
		return connection.sync();
	}

}

