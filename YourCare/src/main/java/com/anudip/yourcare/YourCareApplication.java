package com.anudip.yourcare;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class YourCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(YourCareApplication.class, args);
		
	}

    @Bean
    ModelMapper getModelMapper()
	{
		return new ModelMapper();
	}
	
	
}
