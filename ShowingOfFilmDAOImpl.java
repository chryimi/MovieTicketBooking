package com.movbooking.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movbooking.dao.ShowingOfFilmDAO;
import com.movbooking.entity.ShowingOfFilm;

@Repository
public class ShowingOfFilmDAOImpl implements ShowingOfFilmDAO{
	
	@Autowired
	private SessionFactory sessionfactory;
	
	private Session getCurrentSession() {
		return sessionfactory.getCurrentSession();
	}

	@Override
	public void addShowing(ShowingOfFilm showingOfFilm) {
		getCurrentSession().save(showingOfFilm);
		
	}

	@Override
	public void deleteShowing(Integer showingId) {
		ShowingOfFilm showingOfFilm = getShowing(showingId);
		if (showingOfFilm != null) {
			getCurrentSession().delete(showingOfFilm);
		}
		
	}

	@Override
	public ShowingOfFilm getShowing(Integer showingId) {
		return (ShowingOfFilm)getCurrentSession().get(ShowingOfFilm.class, showingId);
	}

	@Override
	public void updateShowing(ShowingOfFilm showingOfFilm) {
		getCurrentSession().update(showingOfFilm);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShowingOfFilm> getShowings() {
		return getCurrentSession().createQuery("from ShowingOfFilm").list();
	}

	@Override
	public ShowingOfFilm getShowing(ShowingOfFilm sh) {
		String hql = "from ShowingOfFilm S where S.cinemaId=:cinemaId and S.movie=:movie "
				+ "and S.videoHallNo=:videoHallNo and S.screenTime=:screenTime";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("cinemaId", sh.getCinemaId());
		query.setParameter("movie", sh.getMovie());
		query.setParameter("videoHallNo", sh.getVideoHallNo());
		query.setParameter("screenTime", sh.getScreenTime());
		@SuppressWarnings("unchecked")
		List<ShowingOfFilm> slist = query.list();
		if (!slist.isEmpty()) {
			return slist.get(0);
		}
		return null;
	}

}
