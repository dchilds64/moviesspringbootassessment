package com.springbootapi1.SpringBootApi1.dom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Movies {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String imdbId;
	@Column(nullable = false)
	private String movieTitle;
	@Column(nullable = false)
	private String movieYear;
	@Column(nullable = false)
	private String movieActors;
	@Column(nullable = false)
	private String movieGenre;
	@Column(nullable = false)
	private String movieDirector;
	@Column(nullable = false)
	private String moviePlot;

	public Movies(MovieInfoDom movieInfoDom) {
		this.imdbId = movieInfoDom.getImdbID();
		this.movieTitle = movieInfoDom.getTitle();
		this.movieYear = movieInfoDom.getYear();
		this.movieActors = movieInfoDom.getActors();
		this.movieGenre = movieInfoDom.getGenre();
		this.movieDirector = movieInfoDom.getDirector();
		this.moviePlot = movieInfoDom.getPlot();
	}

	public Movies() {

	}
}
