package com.movbooking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.movbooking.dao.ArrangementDAO;
import com.movbooking.entity.Arrangement;

@Service
@Transactional
public class ArrangementService {
	
	@Autowired
	private ArrangementDAO arrangementDAO;
	
	public void addArrangement(Arrangement arrangement) {
		arrangementDAO.addArrangement(arrangement);
	}
}
