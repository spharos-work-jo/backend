package com.workjo.pointapp.config;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperBean {

	private final ModelMapper modelMapper = new ModelMapper();


	@Bean
	public ModelMapper modelMapper() {
		return modelMapper;
	}


	@Bean
	public ModelMapper privateStrictModelMapper() {
		modelMapper.getConfiguration()
			.setFieldMatchingEnabled(true)
			.setMatchingStrategy(MatchingStrategies.STRICT)
			.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
		return modelMapper;
	}

}
