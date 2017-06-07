package com.movbooking.services;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movbooking.dao.ShowingOfFilmDAO;
import com.movbooking.dao.TicktetDAO;
import com.movbooking.entity.Movie;
import com.movbooking.entity.ShowingOfFilm;
import com.movbooking.entity.Ticket;

@Service
@Transactional
public class ChooseSeatService {
	@Autowired
	private ShowingOfFilmDAO showingOfFilmDAO;
	
	@Autowired
	private TicktetDAO ticketDao;
	
	public String getShowingInfo(Integer showingID) {
		ShowingOfFilm showOfFilm = showingOfFilmDAO.getShowing(showingID);
		Movie movie = showOfFilm.getMovie();
		
		String movieName = movie.getName();
		
		Calendar cale = showOfFilm.getScreenTime();
		Date tasktime = cale.getTime();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		Integer year = cale.get(Calendar.YEAR);
		Integer month = cale.get(Calendar.MONTH)+1;
		Integer day = cale.get(Calendar.DATE);
		String time = df.format(tasktime);
		String movieTime = year.toString() + "年" + month.toString() + "月"
				+ day.toString() + "日" + " " + time;
		
		Float price = showOfFilm.getPrice();
		DecimalFormat dfd = new DecimalFormat("0.00");
		
		Integer rows = showOfFilm.getMaxRow();
		Integer cols = showOfFilm.getMaxCol();
		String seatMatrix = showOfFilm.getFormattedSeat();
		
		return "{"
				+ "\"showingId\":\"" + showingID.toString() + "\"" + ","
		        + "\"movieName\":\"" + movieName + "\"" + ","
				+ "\"movieTime\":\"" + movieTime + "\"" + ","
		        + "\"ticketPrice\":" + dfd.format(price) + ","
				+ "\"row\":" + rows.toString() + ","
				+ "\"col\":" + cols.toString() + ","
				+ "\"seatStatus\":" + seatMatrix
				+ "}";
	}
	
	public boolean bookSeat(int row, int col, Integer showingID, Integer userId) {
		ShowingOfFilm showOfFilm = showingOfFilmDAO.getShowing(showingID);
		boolean success = showOfFilm.bookSeat(row-1, col-1);
		showingOfFilmDAO.updateShowing(showOfFilm);
		
		Ticket ticket = new Ticket(userId, showingID, row, col);
		ticketDao.addTicket(ticket);
		return success;
	}
	
}
