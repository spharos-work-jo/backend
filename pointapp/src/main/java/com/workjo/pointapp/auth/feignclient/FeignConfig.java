package com.workjo.pointapp.auth.feignclient;


import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;


public class FeignConfig {

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}


	@Bean
	public RequestInterceptor basicFeignRequestInterceptor() {
		return new ColonInterceptor();
	}


	public static class ColonInterceptor implements RequestInterceptor {

		@Override
		public void apply(RequestTemplate template) {
			template.uri(template.path().replaceAll("%3A", ":"));
		}

	}

}