package com.movbooking.dao;

import java.util.List;

import com.movbooking.entity.Movie;

public interface MovieDAO {
	public void addMovie(Movie movie);
	public void deleteMovie(Integer movieId);
	public Movie getMovie(Integer movieId);
	public void updataMovie(Movie movie);
	public List<Movie> getMovies();
}
