package com.workjo.pointapp.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.time.Duration;


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

}

