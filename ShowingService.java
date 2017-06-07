package com.movbooking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.movbooking.dao.ShowingOfFilmDAO;
import com.movbooking.entity.ShowingOfFilm;

@Service
@Transactional
public class ShowingService {
	@Autowired
	private ShowingOfFilmDAO showingOfFilmDAO;
	
	public void addShowOfFilm(ShowingOfFilm showingOfFilm) {
		showingOfFilmDAO.addShowing(showingOfFilm);
	}
	
	public ShowingOfFilm getShowingOfFilm(Integer showingId) {
		return showingOfFilmDAO.getShowing(showingId);
	}
	
	public ShowingOfFilm getShowingOfFilm(ShowingOfFilm sh) {
		return showingOfFilmDAO.getShowing(sh);
	}
}
