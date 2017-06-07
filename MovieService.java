package com.movbooking.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.movbooking.dao.MovieDAO;
import com.movbooking.entity.Movie;

@Service
@Transactional
public class MovieService {
	@Autowired
	private MovieDAO movieDAO;
	
	public void addMovie(Movie movie){
		movieDAO.addMovie(movie);
	}
	
	public Movie getMovie(Integer movieId) {
		return movieDAO.getMovie(movieId);
	}


	public String getCurrentMovie(){
		List<Movie> movies = movieDAO.getMovies();
		
		
		Calendar today = Calendar.getInstance();
		
		int size = movies.size();

		for(int i = 0; i < size; i++){
			Calendar date = movies.get(i).getReleaseDate();
			if(date.compareTo(today) > 0){
				movies.remove(i);
				i--;
				size--;
			}

		}

		String result = "";
		size = movies.size();

		if(size != 0){
			Movie movie = movies.get(0);
			result = movie.getMovieId() + "," + movie.computeMovieImgPath() + ","
			+ movie.getName();

			for(int i = 1; i < size; i++){
				movie = movies.get(i);
				result = result + ";" + movie.getMovieId() + "," +
				movie.computeMovieImgPath() + "," + movie.getName();
			} 
		}
		
		return result;
	}
	
	public String getLaterMovie(){
		List<Movie> movies = movieDAO.getMovies();
		
		Calendar today = Calendar.getInstance();
		
		int size = movies.size();

		for(int i = 0; i < size; i++) {
			Calendar date = movies.get(i).getReleaseDate();
			if(date.compareTo(today) <= 0){
				movies.remove(i);
				i--;
				size--;
			}

		}

		String result = "";
		size = movies.size();

		if(size != 0) {
			Movie movie = movies.get(0);
			result = movie.getMovieId() + "," + movie.computeMovieImgPath() + ","
			+ movie.getName();

			for(int i = 1; i < size; i++) {
				movie = movies.get(i);
				result = result + ";" + movie.getMovieId() + "," +
				movie.computeMovieImgPath() + "," + movie.getName();
			} 
		}
		
		return result;
	}

	public String getMovieSummary(Integer movieId){
		
		System.out.println(movieId);
		Movie movie = movieDAO.getMovie(movieId);
		
		System.out.println(movie);

		String result = "";

		result = movie.computeMovieImgPath() + ";"
		+ movie.getName() + ";" + movie.getDirector() + ";"
		+ movie.getMainActor() + ";" + movie.getShowingType();

		return result;
	}


	public String getMovieDscription(Integer movieId){
		Movie movie = movieDAO.getMovie(movieId);

		return movie.getMovieDescription();
	}

}
