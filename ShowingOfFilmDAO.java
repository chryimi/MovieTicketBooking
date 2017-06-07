package com.movbooking.dao;

import java.util.List;

import com.movbooking.entity.ShowingOfFilm;


public interface ShowingOfFilmDAO {
    public void addShowing(ShowingOfFilm showingOfFilm);
    public void deleteShowing(Integer showingId);
    public ShowingOfFilm getShowing(Integer showingId);
    public void updateShowing(ShowingOfFilm showingOfFilm);
    public List<ShowingOfFilm> getShowings();
    public ShowingOfFilm getShowing(ShowingOfFilm sh);
}
