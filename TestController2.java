package com.movbooking.controller;

import java.io.BufferedReader;
import java.io.FileReader;
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

import com.movbooking.dao.AreaDAO;
import com.movbooking.entity.Area;
import com.movbooking.entity.Arrangement;
import com.movbooking.entity.Cinema;
import com.movbooking.entity.CinemaDistribution;
import com.movbooking.entity.Movie;
import com.movbooking.entity.ShowingOfFilm;
import com.movbooking.entity.VideoHall;
import com.movbooking.services.AreaService;
import com.movbooking.services.ArrangementService;
import com.movbooking.services.CinemaDistributionService;
import com.movbooking.services.CinemaService;
import com.movbooking.services.HallService;
import com.movbooking.services.MovieService;
import com.movbooking.services.ShowingService;
import com.movbooking.util.DateStringToCalendar;

@Controller
public class TestController2 {
	private static Logger logger = Logger.getLogger(UserController.class.getName());
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private HallService hallService;
	
	@Autowired
	private ShowingService showingService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private CinemaDistributionService cinemaDistributionService;
	
	@Autowired
	private ArrangementService arrangementService;
	
	@RequestMapping(value="/test/addAll", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Callable<String> addAll(HttpServletRequest request, HttpServletResponse response) {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				StringBuilder sball = new StringBuilder();
				String[] paths = {"D:/eclipseWorkspace/MovieBooking/WebContent/data/test/movieInfo/1.txt",
						"D:/eclipseWorkspace/MovieBooking/WebContent/data/test/movieInfo/2.txt",
						"D:/eclipseWorkspace/MovieBooking/WebContent/data/test/movieInfo/3.txt",
						"D:/eclipseWorkspace/MovieBooking/WebContent/data/test/movieInfo/4.txt",
						"D:/eclipseWorkspace/MovieBooking/WebContent/data/test/movieInfo/5.txt",
						"D:/eclipseWorkspace/MovieBooking/WebContent/data/test/movieInfo/6.txt",
						"D:/eclipseWorkspace/MovieBooking/WebContent/data/test/movieInfo/7.txt",
						"D:/eclipseWorkspace/MovieBooking/WebContent/data/test/movieInfo/8.txt"
						
				};
				System.out.println("-----------"+ "path length " + paths.length);
				for (int i = 0; i < paths.length; i++) {
					System.out.println(paths[i]);
					String[] content = new String[7];
					BufferedReader in = new BufferedReader(new FileReader(paths[i]));
					String s;
					int j = 0;
					while((s = in.readLine()) != null && j < 7) {
						content[j] = s;
						j++;
					}
					in.close();
					String movieName = content[0].substring(1);
					String director = content[1];
					String mainActor = content[2];
					String type = content[3];
					Integer minutes = Integer.parseInt(content[4]);
					String dateString = content[5];
					String desc = content[6];
					Calendar date = DateStringToCalendar.dateStringToCalendar(dateString);
					movieService.addMovie(new Movie(movieName, director, mainActor, type, minutes, date, desc));
				}
				Movie movie = movieService.getMovie(1);
				
				sball.append(movie.toString());
				sball.append("\n");
				sball.append("\n");
				
				/////////////////////////////////////////////////////
				String[] paths5 = {"D:/eclipseWorkspace/MovieBooking/WebContent/data/test/Area/area.txt"};
				for (int i = 0; i < paths5.length; i++) {
					String s;
					String city;
					String region;
					Area area = new Area();
					BufferedReader in = new BufferedReader(new FileReader(paths5[i]));
					while((s = in.readLine()) != null) {
						if (s.trim().split(" ").length != 2) {
							continue;
						}
						city = s.trim().split(" ")[0];
						region = s.trim().split(" ")[1];
						area.setCity(city);
						area.setArea(region);
						areaService.addArea(area);
					}
					
					in.close();
					
				}
				///////////////////////////////////////////////////////
				String[] paths2 = {"D:/eclipseWorkspace/MovieBooking/WebContent/data/test/cinemaInfo/cinemaInfo.txt"};
				for (int i = 0; i < paths2.length; i++) {
					String[] content = new String[6];
					BufferedReader in = new BufferedReader(new FileReader(paths2[i]));
					String s;
					int j = 0;
					String cinemaName;
					String city;
					String area;
					String location;
					String desc;
					String cimg;
					while((s = in.readLine()) != null && j < 6) {
						content[j] = s;
						j++;
						if (j == 6) {
							j = 0;
							cinemaName = content[1];
							city = content[2].trim();
							area = content[3].trim();
							location = content[4];
							desc = content[5];
							cimg = "1.jpg";
							cinemaService.addCinema(new Cinema(cinemaName, location, desc, cimg, city, area));
						}
					}
					in.close();
				}
				Cinema cinema = cinemaService.getCinemaById(1);
				
				sball.append(cinema.toString());
				sball.append("\n");
				sball.append("\n");
				////////////////////////////////////////////////////
				String[] paths3 = {"D:/eclipseWorkspace/MovieBooking/WebContent/data/test/hallInfo/hallInfo.txt"};
				Integer cinemaid;
				Integer rowNum;
				Integer hallNo;
				Integer colNum;
				String seatMatrix;
				for (int i = 0; i < paths3.length; i++) {
					String s;
					BufferedReader in = new BufferedReader(new FileReader(paths3[i]));
					String[] content = new String[5];
					int j = 0;
					while((s = in.readLine()) != null && j < 5) {
						content[j] = s;
						j++;
						if (j == 5) {
							j = 0;
							cinemaid = Integer.parseInt(content[1]);
							hallNo = Integer.parseInt(content[2]);
							rowNum = Integer.parseInt(content[3]);
							colNum = Integer.parseInt(content[4]);
							
							StringBuilder sb = new StringBuilder();
							for (int r = 0; r < rowNum; r++) {
								s = in.readLine();
								if (s.length() > colNum) {
									s = s.substring(0, colNum);
								}
								if (s.length() < colNum) {
									int initLen = s.length();
									for (int k = initLen; k <= colNum; k++) {
										s += "a";
									}
								}
								sb.append(s);
							}
							seatMatrix = new String(sb);
							hallService.addHall(new VideoHall(cinemaid, hallNo, rowNum, colNum, seatMatrix));
						}
					}
					in.close();
				}
				VideoHall videoHall = hallService.getHall(1, 1);
				
				sball.append(videoHall.toString());
				sball.append("\n");
				sball.append("\n");
				/////////////////////////////////////////////////////
				String[] paths7 = {"D:/eclipseWorkspace/MovieBooking/WebContent/data/test/cinema_movie_map/cinema_movie_map.txt"};
				for (int i = 0; i < paths7.length; i++) {
					String s;
					Integer movieId;
					Integer cineamId;
					BufferedReader in = new BufferedReader(new FileReader(paths7[i]));
					while((s = in.readLine()) != null) {
						if (s.trim().split(" ").length != 2) {
							continue;
						}
						cineamId = Integer.parseInt(s.split(" ")[0]);
						movieId = Integer.parseInt(s.split(" ")[1]);
						Cinema tcinema = cinemaService.getCinemaById(cineamId);
						CinemaDistribution cinemaDistribution = new CinemaDistribution(tcinema.getCity().trim(), tcinema.getArea().trim(), movieId, tcinema);
						cinemaDistributionService.addDistribution(cinemaDistribution);
					}
					
					in.close();
					
				}
				////////////////////////////////////////////////////
				String[] paths4 = {"D:/eclipseWorkspace/MovieBooking/WebContent/data/test/showingInfo/1.txt"};
				for (int i = 0; i < paths4.length; i++) {
					String[] content = new String[9];
					BufferedReader in = new BufferedReader(new FileReader(paths4[i]));
					String s;
					int j = 0;
					Integer cinemaId;
					Integer movieid;
					Calendar screenTime;
					Calendar currTime;
					Calendar endTime;
					Integer videoHallNo;
					String language;
					float price;
					int showingType;
					Calendar newScreenTime;
					Calendar newEndTime;
					
					while((s = in.readLine()) != null && j < 9) {
						content[j] = s;
						j++;
						if (j == 9) {
							j = 0;
							cinemaId = Integer.parseInt(content[1]);
							movieid = Integer.parseInt(content[2]);
							currTime = Calendar.getInstance();
							screenTime = DateStringToCalendar.timeStringToCalendar(content[3]);
							endTime = DateStringToCalendar.timeStringToCalendar(content[4]);
							
							screenTime.set(Calendar.YEAR, currTime.get(Calendar.YEAR));
							endTime.set(Calendar.YEAR, currTime.get(Calendar.YEAR));
							screenTime.set(Calendar.MONTH, currTime.get(Calendar.MONTH));
						    endTime.set(Calendar.MONTH, currTime.get(Calendar.MONTH));
							screenTime.set(Calendar.DAY_OF_MONTH, currTime.get(Calendar.DAY_OF_MONTH));
							endTime.set(Calendar.DAY_OF_MONTH, currTime.get(Calendar.DAY_OF_MONTH));
							
							videoHallNo = Integer.parseInt(content[5]);
							language = content[6];
							price = Float.parseFloat(content[7]);
							showingType = Integer.parseInt(content[8]);
							
							Movie movie2 = movieService.getMovie(movieid);
							ShowingOfFilm showingOfFilm = new ShowingOfFilm(movie2, cinemaId, screenTime, endTime, videoHallNo, language, price, showingType);
							showingOfFilm = hallService.calcMaxColAndRowForShowing(showingOfFilm);

							showingService.addShowOfFilm(showingOfFilm);			
							arrangementService.addArrangement(new Arrangement(movieid, cinemaId, showingOfFilm));
							////////////////////////////////////////////////////
							newScreenTime = showingOfFilm.getScreenTime();
							newEndTime = showingOfFilm.getEndTime();
							newScreenTime.set(Calendar.MONTH, 7);
							newScreenTime.set(Calendar.DAY_OF_MONTH, 1);
							newEndTime.set(Calendar.MONTH, 7);
							newEndTime.set(Calendar.DAY_OF_MONTH, 1);
							showingOfFilm.setScreenTime(newScreenTime);
							showingOfFilm.setEndTime(newEndTime);
							showingService.addShowOfFilm(showingOfFilm);			
							arrangementService.addArrangement(new Arrangement(movieid, cinemaId, showingOfFilm));
							
							newScreenTime = showingOfFilm.getScreenTime();
							newEndTime = showingOfFilm.getEndTime();
							newScreenTime.set(Calendar.MONTH, 7);
							newScreenTime.set(Calendar.DAY_OF_MONTH, 2);
							newEndTime.set(Calendar.MONTH, 7);
							newEndTime.set(Calendar.DAY_OF_MONTH, 2);
							showingOfFilm.setScreenTime(newScreenTime);
							showingOfFilm.setEndTime(newEndTime);
							showingService.addShowOfFilm(showingOfFilm);			
							arrangementService.addArrangement(new Arrangement(movieid, cinemaId, showingOfFilm));
							
							newScreenTime = showingOfFilm.getScreenTime();
							newEndTime = showingOfFilm.getEndTime();
							newScreenTime.set(Calendar.MONTH, 6);
							newScreenTime.set(Calendar.DAY_OF_MONTH, 31);
							newEndTime.set(Calendar.MONTH, 6);
							newEndTime.set(Calendar.DAY_OF_MONTH, 31);
							showingOfFilm.setScreenTime(newScreenTime);
							showingOfFilm.setEndTime(newEndTime);
							showingService.addShowOfFilm(showingOfFilm);			
							arrangementService.addArrangement(new Arrangement(movieid, cinemaId, showingOfFilm));
							
							newScreenTime = showingOfFilm.getScreenTime();
							newEndTime = showingOfFilm.getEndTime();
							newScreenTime.set(Calendar.MONTH, 6);
							newScreenTime.set(Calendar.DAY_OF_MONTH, 16);
							newEndTime.set(Calendar.MONTH, 6);
							newEndTime.set(Calendar.DAY_OF_MONTH, 16);
							showingOfFilm.setScreenTime(newScreenTime);
							showingOfFilm.setEndTime(newEndTime);
							showingService.addShowOfFilm(showingOfFilm);			
							arrangementService.addArrangement(new Arrangement(movieid, cinemaId, showingOfFilm));
						}
					}
					in.close();
				}
				ShowingOfFilm showingOfFilm = showingService.getShowingOfFilm(1);
				sball.append(showingOfFilm.toString());
				sball.append("\n");
				sball.append("\n");
				///////////////////////////////////////////////////////
				
				
				///////////////////////////////////////////////////////
				
				return sball.toString();	
			}
		};
	}

}


