package com.springbootapi1.SpringBootApi1.controller;

import com.springbootapi1.SpringBootApi1.service.MovieInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieInfoConfiguration {

	@Bean
	public MovieInfoService movieInfoService() {
		return new MovieInfoService();
	}
}
