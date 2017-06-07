package com.movbooking.dao;
import java.util.List;

import com.movbooking.entity.VideoHall;
import com.movbooking.util.JoinClassKeyOfVideoHall;

public interface VideoHallDAO {
	public void addVideoHall(VideoHall videoHall);
	public void deleteVideoHall(JoinClassKeyOfVideoHall videoHallKey);
	public VideoHall getVideoHall(JoinClassKeyOfVideoHall videoHallKey);
	public void updateVideoHall(VideoHall videoHall);
	public List<VideoHall> getVideoHalls();
}
