package com.workjo.pointapp.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;


@Configuration
@EnableRedisRepositories(basePackages = "com.workjo.pointapp")
public class RedisConfig {

	@Value("${spring.data.redis.port}")
	public int port;

	@Value("${spring.data.redis.host}")
	public String host;

	@Value("${spring.data.redis.password}")
	public String password;


	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
		redisStandaloneConfiguration.setPassword(password);
		return new LettuceConnectionFactory(redisStandaloneConfiguration);
	}

}

