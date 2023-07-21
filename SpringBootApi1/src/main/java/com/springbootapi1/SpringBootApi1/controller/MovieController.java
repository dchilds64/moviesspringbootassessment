package com.springbootapi1.SpringBootApi1.controller;

import com.springbootapi1.SpringBootApi1.dom.Movies;
import com.springbootapi1.SpringBootApi1.repository.MoviesRepository;
import com.springbootapi1.SpringBootApi1.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private MoviesRepository moviesRepository;
	private MovieService movieService;

	@Autowired
	public MovieController(MoviesRepository moviesRepository, MovieService movieService) {
		this.movieService = movieService;
		this.moviesRepository = moviesRepository;
	}

	@GetMapping("/info")
	public ResponseEntity getMovieInfo(@RequestParam String title) {
		movieService.populateMovies(title);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<List<Movies>> getMovieByImdbId(
			@RequestParam(required = false, defaultValue = "") String imdbId,
			@RequestParam(required = false, defaultValue = "") String title,
			@RequestParam(required = false, defaultValue = "") String actors,
			@RequestParam(required = false, defaultValue = "") String genre) {
		List<Movies> movies = new ArrayList<>();
		if (!imdbId.equals("")) {
			movies = movieService.getMovieByImdbId(imdbId);
		} else if (!title.equals("")) {
			movies = movieService.getMovieByTitle(title);
		} else if (!actors.equals("")) {
			movies = movieService.getMovieByActors(actors);
		} else if (!genre.equals("")) {
			movies = movieService.getMovieByGenre(genre);
		}

		if (movies.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(movies);
		}
	}

	@PostMapping
	public ResponseEntity addMovie(@RequestBody Movies movie) {
		moviesRepository.save(movie);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity deleteMovie(@RequestParam String imdbId) {
		long count = movieService.deleteMovie(imdbId);
		if (count != 0) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
