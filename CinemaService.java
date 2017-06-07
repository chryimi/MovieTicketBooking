package com.movbooking.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.movbooking.dao.AreaDAO;
import com.movbooking.dao.ArrangementDAO;
import com.movbooking.dao.CinemaDAO;
import com.movbooking.dao.CinemaDistributionDAO;
import com.movbooking.dao.MovieDAO;
import com.movbooking.dao.ShowingOfFilmDAO;
import com.movbooking.dao.VideoHallDAO;
import com.movbooking.entity.Area;
import com.movbooking.entity.Arrangement;
import com.movbooking.entity.Cinema;
import com.movbooking.entity.CinemaDistribution;
import com.movbooking.entity.Movie;
import com.movbooking.entity.ShowingOfFilm;
import com.movbooking.entity.VideoHall;


@Service
@Transactional
public class CinemaService {
	@Autowired
	private AreaDAO areaDAO;
	
	@Autowired
	private CinemaDistributionDAO cinemaDistributionDAO;
	
	@Autowired
	private ArrangementDAO arrangementDAO;
	
	@Autowired
	private CinemaDAO cinemaDAO;
	
	@Autowired
	private VideoHallDAO videoHallDAO;
	
	@Autowired ShowingOfFilmDAO showingOfFilmDAO;
	
	@Autowired
	MovieDAO movieDAO;
	
	public Movie getMovieById(Integer id) {
		return movieDAO.getMovie(id);
	}
	
	public void addShowingOfFilm(ShowingOfFilm showingOfFilm){
		showingOfFilmDAO.addShowing(showingOfFilm);
	}
	
	public Cinema getCinemaById(Integer id){
		return cinemaDAO.getCinema(id);
	}
	
	public void addCinema(Cinema cinema){
		cinemaDAO.addCinema(cinema);
	}
	
	public void addArrangement(Arrangement arrangement){
		arrangementDAO.addArrangement(arrangement);
	}
	
	public void addVideoHall(VideoHall videoHall){
		videoHallDAO.addVideoHall(videoHall);
	}
	
	public String getAreaByCity(String city){
		
		List<Area> areas = areaDAO.getAreas(city);
		
		String result = "";
		
		int size = areas.size();
		
		if(size != 0){
			result = areas.get(0).getArea();
			for(int i = 1; i < size; i++){
				result = result + ";" + areas.get(i).getArea();
			}
		}
		return result;
	}
	
	public String getCinemaByLocation(String city, String area, Integer movieId){
		HashMap<String, String> languageMap = new HashMap<String, String>();
		languageMap.put("广州", "Guangzhou");
		languageMap.put("深圳", "Shenzhen");
		List<CinemaDistribution> distributions = cinemaDistributionDAO.getCinemaDistributions(languageMap.get(city), area, movieId);
		
		
		String result = "";
		
		int size = distributions.size();
		System.out.println("---------" + size);
		
		if(size != 0){
			result = distributions.get(0).getCinema().getCinemaId() + "|" +
					distributions.get(0).getCinema().getName();
			for(int i = 1; i < size; i++){
				result = result + ";" + distributions.get(i).getCinema().getCinemaId()
						+ "|" + distributions.get(i).getCinema().getName();
			}
		}
		return result;
	}
	
	
	public String getSession(Integer cinemaId, Integer movieId, String dateString){
		

		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");

		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		
		
		List<Arrangement> arrangements = arrangementDAO.getArrangements(cinemaId, movieId);
		
		

		
		int size = arrangements.size();
		
		for(int i = 0; i < size; i++){
			System.out.println(arrangements.get(i));
			System.out.println(arrangements.get(i).getShowing());
			System.out.println(arrangements.get(i).getShowing().getScreenTime());
			Calendar cal =  arrangements.get(i).getShowing().getScreenTime();
			
			if(cal.get(Calendar.YEAR) != calendar.get(Calendar.YEAR)
					|| cal.get(Calendar.MONTH) != calendar.get(Calendar.MONTH)
					|| cal.get(Calendar.DAY_OF_MONTH) != calendar.get(Calendar.DAY_OF_MONTH) ){
				arrangements.remove(i);
				i--;
				size--;
				
			}
			
		}
		
		String result = "";
		size = arrangements.size();
		if(size != 0){
			sdf= new SimpleDateFormat("HH:mm");
			ShowingOfFilm showing = arrangements.get(0).getShowing();
			String dateStr1 = sdf.format(showing.getScreenTime().getTime());
			String dateStr2 = sdf.format(showing.getEndTime().getTime());
			
			result = dateStr1 + "|" + dateStr2 + "|" + showing.getLanguage() + "|"
					+ showing.getVideoHallNo() + "|" + showing.getPrice() + "|" + 
					showing.getShowingId();
			for(int i = 1; i < size; i++){
				
				showing = arrangements.get(i).getShowing();
				dateStr1 = sdf.format(showing.getScreenTime().getTime());
				dateStr2 = sdf.format(showing.getEndTime().getTime());
				
				result = result + ";" + dateStr1 + "|" + dateStr2 + "|" + showing.getLanguage() + "|"
						+ showing.getVideoHallNo() + "|" + showing.getPrice() + "|" + 
						showing.getShowingId();
			}
		}
		return result;
		
	}
	
	
}
