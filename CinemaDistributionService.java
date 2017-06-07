package com.movbooking.services;

import java.security.PrivilegedActionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movbooking.dao.CinemaDistributionDAO;
import com.movbooking.entity.CinemaDistribution;

@Service
@Transactional
public class CinemaDistributionService {
	@Autowired
	private CinemaDistributionDAO cinemaDistributionDAO;
	public void addDistribution(CinemaDistribution cinemaDistribution){
		cinemaDistributionDAO.addCinemaDistribution(cinemaDistribution);
	}
	
}
