package com.movbooking.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movbooking.dao.ArrangementDAO;
import com.movbooking.entity.Arrangement;

@Repository
public class ArrangementDAOImpl implements ArrangementDAO {
	
	@Autowired
	private SessionFactory sessionfactory;
	
	private Session getCurrentSession() {
		return sessionfactory.getCurrentSession();
	}

	@Override
	public void addArrangement(Arrangement arrangement) {
		getCurrentSession().save(arrangement);

	}

	@Override
	public void deleteArrangement(Arrangement arrangement) {
		getCurrentSession().delete(arrangement);

	}

	@Override
	public List<Arrangement> getArrangements(Integer cinemaId, Integer movieId) {
		String hql = "FROM Arrangement A WHERE A.cinemaId = :cinemaId and A.movieId = :movieId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("cinemaId", cinemaId);
		query.setParameter("movieId", movieId);
		@SuppressWarnings("unchecked")
		List<Arrangement> result = query.list();
		return result;
	}

	@Override
	public void updateArrangement(Arrangement arrangement) {
		getCurrentSession().update(arrangement);

	}

}
