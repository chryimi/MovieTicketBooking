package com.movbooking.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movbooking.dao.AreaDAO;
import com.movbooking.entity.Area;

@Repository
public class AreaDAOImpl implements AreaDAO {

	@Autowired
	private SessionFactory sessionfactory;
	
	private Session getCurrentSession() {
		return sessionfactory.getCurrentSession();
	}
	
	@Override
	public void addArea(Area area) {
		getCurrentSession().save(area);

	}

	@Override
	public void deleteArea(Area area) {
		getCurrentSession().delete(area);

	}

	@Override
	public List<Area> getAreas(String city) {
		city = "Guangzhou";
		System.out.println("getAreas city: "+city);
		String hql = "FROM Area as A WHERE A.city = :city";
		//String hql = "FROM Area as A";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("city", city);
		//query.setParameter("id", "1");
		@SuppressWarnings("unchecked")
		List<Area> result = query.list();
		/*for (Area area : result) {
			System.out.println(area.getCity());
		}*/
		return result;
	}

	@Override
	public void updateArea(Area area) {
		getCurrentSession().update(area);
	}

}
