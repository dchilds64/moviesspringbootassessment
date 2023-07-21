package com.springbootapi1.SpringBootApi1.service;

import com.springbootapi1.SpringBootApi1.dom.Movies;
import com.springbootapi1.SpringBootApi1.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

	private MovieInfoService movieInfoService;
	private MoviesRepository moviesRepository;

	@Autowired
	public MovieService(MoviesRepository moviesRepository, MovieInfoService movieInfoService) {
		this.moviesRepository = moviesRepository;
		this.movieInfoService = movieInfoService;
	}

	public List<Movies> getMovieByImdbId(String imdbId) {
		return moviesRepository.findAllByImdbId(imdbId);
	}

	public List<Movies> getMovieByTitle(String title) {
		return moviesRepository.findAllByMovieTitle(title);
	}

	public List<Movies> getMovieByActors(String actors) {
		return moviesRepository.findAllByMovieActors(actors);
	}

	public List<Movies> getMovieByGenre(String genre) {
		return moviesRepository.findAllByMovieGenre(genre);
	}

	public long deleteMovie(String imdbId) {
		return moviesRepository.deleteAllByImdbId(imdbId);
	}

	public void addMovie(Movies movies) {
		moviesRepository.save(movies);
	}

	public void populateMovies(String title) {
		List<Movies> movies = movieInfoService.getMovieDataByTitle(title);
		for (Movies movie : movies) {
			moviesRepository.save(movie);
		}
	}
}
