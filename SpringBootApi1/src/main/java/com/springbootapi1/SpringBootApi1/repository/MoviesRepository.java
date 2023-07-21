package com.springbootapi1.SpringBootApi1.repository;

import com.springbootapi1.SpringBootApi1.dom.Movies;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends CrudRepository<Movies, String> {
	List<Movies> findAllByImdbId(String imdbId);
	List<Movies> findAllByMovieTitle(String title);
	List<Movies> findAllByMovieActors(String actors);
	List<Movies> findAllByMovieGenre(String genre);
	@Transactional
	long deleteAllByImdbId(String imdbId);
}
