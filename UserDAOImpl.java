package com.movbooking.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movbooking.dao.UserDAO;
import com.movbooking.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionfactory;
	
	private Session getCurrentSession() {
		return sessionfactory.getCurrentSession();
	}

	@Override
	public User getUserById(Integer id) {
		User user = null;
		user = (User)getCurrentSession().get(User.class, id);
		return user;
	}
	
	@Override
	public void addUser(User user) {
		getCurrentSession().save(user);
	}

	@Override
	public void deleteUser(String userName) {
		User user = getUser(userName);
		if (user != null) {
			getCurrentSession().delete(user);
		}
	}

	@Override
	public User getUser(String userName) {
		// TODO Auto-generated method stub
		String hql = "FROM User U WHERE U.userName = :userName";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("userName", userName);
		User user = null;
		@SuppressWarnings("unchecked")
		List<User> result = query.list();
		if (!result.isEmpty()) {
			user = result.get(0);
		}
		return user;
	}

	@Override
	public void updateUser(String originUserName, User user) {
		User userToUpdate = getUser(originUserName);
		if (userToUpdate != null) {
			userToUpdate.setUserName(user.getUserName());
			userToUpdate.setPassword(user.getPassword());
			getCurrentSession().update(userToUpdate);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		return getCurrentSession().createQuery("from User").list();
	}

}
