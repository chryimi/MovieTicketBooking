package com.movbooking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.movbooking.dao.VideoHallDAO;
import com.movbooking.entity.ShowingOfFilm;
import com.movbooking.entity.VideoHall;
import com.movbooking.util.JoinClassKeyOfVideoHall;

@Service
@Transactional
public class HallService {
	@Autowired
	private VideoHallDAO videoHallDAO;
	
	public void addHall(VideoHall hall) {
		videoHallDAO.addVideoHall(hall);
	}
	
	public VideoHall getHall(Integer cid, Integer hno) {
		JoinClassKeyOfVideoHall jkv = new JoinClassKeyOfVideoHall(cid, hno);
		return videoHallDAO.getVideoHall(jkv);
	}
	
	public ShowingOfFilm calcMaxColAndRowForShowing(ShowingOfFilm showingOfFilm) {
		JoinClassKeyOfVideoHall key = new JoinClassKeyOfVideoHall(showingOfFilm.getCinemaId(), showingOfFilm.getVideoHallNo());
		VideoHall videoHall = videoHallDAO.getVideoHall(key);
		showingOfFilm.setMaxRow(videoHall.getMaxRow());
		showingOfFilm.setMaxCol(videoHall.getMaxColumn());
		showingOfFilm.setSeatMatrix(videoHall.getSeatMatrix());
		return showingOfFilm;
	}
}
