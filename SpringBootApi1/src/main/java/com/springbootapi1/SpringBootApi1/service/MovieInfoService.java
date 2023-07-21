package com.springbootapi1.SpringBootApi1.service;

import com.springbootapi1.SpringBootApi1.dom.MovieInfoDom;
import com.springbootapi1.SpringBootApi1.dom.Movies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class MovieInfoService {
	private final String moviesBaseURL = "https://gateway.maverik.com/movie/api/movie/";

	public List<Movies> getMovieDataByTitle(String title) {
		List<Movies> movies = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		String titleUrl = moviesBaseURL + "title/" + title + "?source=web";
		String idUrl = moviesBaseURL + "{ID}?source=web";

		ResponseEntity<MovieInfoDom[]> moviesResponse = restTemplate.getForEntity(titleUrl, MovieInfoDom[].class);

		for (MovieInfoDom movieInfoDom : moviesResponse.getBody()) {
			ResponseEntity<MovieInfoDom> response = restTemplate.getForEntity(idUrl.replace("{ID}", movieInfoDom.getImdbID()),  MovieInfoDom.class);
			movies.add(new Movies(response.getBody()));
		}

		return movies;
	}
}
