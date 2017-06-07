package com.movbooking.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movbooking.dao.MovieDAO;
import com.movbooking.entity.Movie;

@Repository
public class MovieDAOImpl implements MovieDAO{
	@Autowired
	private SessionFactory sessionfactory;
	
	private Session getCurrentSession() {
		return sessionfactory.getCurrentSession();
	}
	
	
	@Override
	public void addMovie(Movie movie) {
		getCurrentSession().save(movie);
		
	}

	@Override
	public void deleteMovie(Integer movieId) {
		Movie movieToDelete = getMovie(movieId);
		if (movieToDelete != null) {
			getCurrentSession().delete(movieToDelete);
		}
		
	}

	@Override
	public Movie getMovie(Integer movieId) {
		return (Movie)getCurrentSession().get(Movie.class, movieId);
	}

	@Override
	public void updataMovie(Movie movie) {
		getCurrentSession().update(movie);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getMovies() {
		return getCurrentSession().createQuery("from Movie").list();
	}

}
