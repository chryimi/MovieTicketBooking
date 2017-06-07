package com.movbooking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.movbooking.dao.AreaDAO;
import com.movbooking.entity.Area;

@Service
@Transactional
public class AreaService {
	@Autowired
	private AreaDAO areaDAO;
	
	public void addArea(Area area){
		areaDAO.addArea(area);
	}
	
}
