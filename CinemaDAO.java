package com.movbooking.dao;

import java.util.List;

import com.movbooking.entity.Cinema;

public interface CinemaDAO {
	public void addCinema(Cinema cinema);
	public void deleteCinema(Integer cinemaId);
	public Cinema getCinema(Integer cinemaId);
	public void updateCinema(Cinema cinema);
	public List<Cinema> getCinemas();
}

