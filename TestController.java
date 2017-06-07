package com.movbooking.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.movbooking.dao.CinemaDistributionDAO;
import com.movbooking.entity.Area;
import com.movbooking.entity.Arrangement;
import com.movbooking.entity.Cinema;
import com.movbooking.entity.CinemaDistribution;
import com.movbooking.entity.Movie;
import com.movbooking.entity.ShowingOfFilm;
import com.movbooking.entity.VideoHall;
import com.movbooking.services.AreaService;
import com.movbooking.services.CinemaDistributionService;
import com.movbooking.services.CinemaService;
import com.movbooking.services.MovieService;


@Controller
public class TestController {
	
	private static Logger logger = Logger.getLogger(UserController.class.getName());
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private CinemaDistributionService cinemaDistributionService;
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping(value="/addArea", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public Callable<String> getArea(HttpServletRequest request, HttpServletResponse response) {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				//response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				String city = request.getParameter("city");
				
				areaService.addArea(new Area("广州", "番禺区"));
				areaService.addArea(new Area("广州", "白云区"));
				
				areaService.addArea(new Area("深圳", "南山区"));
				
				
				//String name, String address, String description, String cinemaImg
				cinemaService.addCinema(new Cinema("国际影城","大学城","很好","1.jpg", "广州", "番禺"));
				
				cinemaService.addCinema(new Cinema("金逸影城","大学城","很好","1.jpg", "广州", "番禺"));
				
				//String name, String director, String mainActor, int showingType, int minutes, Calendar releaseDate,
				//String movieDescription
				Movie movie = new Movie("美国队长","导演","主演名单","2",90,Calendar.getInstance(),"good");
				movieService.addMovie(movie);
				
				//String city, String area, String movieId, Cinema cinema
				Cinema cinema = cinemaService.getCinemaById(1);
				cinemaDistributionService.addDistribution(new CinemaDistribution("广州","番禺区",1,cinema));
				cinemaDistributionService.addDistribution(new CinemaDistribution("广州","番禺区",1,cinemaService.getCinemaById(2)));
				
				//Integer cinemaId, Integer hallNo, Integer maxColumn, Integer maxRow
				cinemaService.addVideoHall(new VideoHall(1,1,3,3, "aaaaaaaaa"));
				
//				Movie movie, Integer cinemaId, Calendar screenTime, Calendar endTime, Integer videoHallNo, String language,
//				float price, int showingType
				ShowingOfFilm showing = new ShowingOfFilm(movie, 1, Calendar.getInstance(), Calendar.getInstance(), 1, "English",1.2f,1);
				
				ShowingOfFilm showing2 = new ShowingOfFilm(movie, 2, Calendar.getInstance(), Calendar.getInstance(), 1, "Chinese",1.2f,1);
				cinemaService.addShowingOfFilm(showing);
				cinemaService.addShowingOfFilm(showing2);
				
				
				cinemaService.addArrangement(new Arrangement(1, 1, showing));
				cinemaService.addArrangement(new Arrangement(1, 2, showing2));
				
				String r = cinemaService.getSession(1, 1, "2016-5-26");
				
				System.out.println(r);
				
				return r;
				
			}
		};
	}
	

}
