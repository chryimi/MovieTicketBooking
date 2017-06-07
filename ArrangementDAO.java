package com.movbooking.dao;

import java.util.Calendar;
import java.util.List;
import com.movbooking.entity.Arrangement;

public interface ArrangementDAO {
	public void addArrangement(Arrangement arrangement);
	public void deleteArrangement(Arrangement arrangement);
	public List<Arrangement> getArrangements(Integer cinemaId, Integer movieId);
	public void updateArrangement(Arrangement arrangement);
}
