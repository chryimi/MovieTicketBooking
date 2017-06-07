package com.movbooking.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movbooking.dao.CinemaDAO;
import com.movbooking.entity.Cinema;

@Repository
public class CinemaDAOImpl implements CinemaDAO {

	@Autowired
	private SessionFactory sessionfactory;
	
	private Session getCurrentSession() {
		return sessionfactory.getCurrentSession();
	}
	
	@Override
	public void addCinema(Cinema cinema) {
		getCurrentSession().save(cinema);

	}

	@Override
	public void deleteCinema(Integer cinemaId) {
		Cinema cinema = getCinema(cinemaId);
		if (cinema != null) {
			getCurrentSession().delete(cinema);
		}

	}

	@Override
	public Cinema getCinema(Integer cinemaId) {
		Cinema cinema = null;
		cinema = (Cinema)getCurrentSession().get(Cinema.class, cinemaId);
		return cinema;
	}

	@Override
	public void updateCinema(Cinema cinema) {
		getCurrentSession().update(cinema);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cinema> getCinemas() {
		return getCurrentSession().createQuery("from Cinema").list();
	}

}
