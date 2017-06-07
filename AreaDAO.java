package com.movbooking.dao;
import java.util.List;

import com.movbooking.entity.Area;


public interface AreaDAO {
	public void addArea(Area area);
	public void deleteArea(Area area);
	
	public List<Area> getAreas(String city);
	public void updateArea(Area area);
}
