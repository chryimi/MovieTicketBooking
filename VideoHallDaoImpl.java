package com.movbooking.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movbooking.dao.VideoHallDAO;
import com.movbooking.entity.VideoHall;
import com.movbooking.util.JoinClassKeyOfVideoHall;

@Repository
public class VideoHallDaoImpl implements VideoHallDAO{
	
	@Autowired
	private SessionFactory sessionfactory;
	
	private Session getCurrentSession() {
		return sessionfactory.getCurrentSession();
	}

	@Override
	public void addVideoHall(VideoHall videoHall) {
		getCurrentSession().save(videoHall);
		
	}

	@Override
	public void deleteVideoHall(JoinClassKeyOfVideoHall videoHallKey) {
		VideoHall videoHallToDel = getVideoHall(videoHallKey);
		if (videoHallToDel != null) {
			getCurrentSession().delete(videoHallToDel);
		}
		
	}

	@Override
	public VideoHall getVideoHall(JoinClassKeyOfVideoHall videoHallKey) {
		return (VideoHall) getCurrentSession().get(VideoHall.class, videoHallKey);
	}

	@Override
	public void updateVideoHall(VideoHall videoHall) {
		getCurrentSession().update(videoHall);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VideoHall> getVideoHalls() {
		return getCurrentSession().createQuery("from VideoHall").list();
	}

}
