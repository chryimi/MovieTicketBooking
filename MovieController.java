package com.movbooking.controller;

import java.util.concurrent.Callable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.movbooking.services.MovieService;
import com.movbooking.util.ConstantUtil;
import com.movbooking.util.ServletUtil;

@Controller
public class MovieController {
	
	private static Logger logger = Logger.getLogger(UserController.class.getName());
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String toHome(HttpServletRequest request) {
			return "home";
	}

	@RequestMapping(value="/introduction", method=RequestMethod.GET)
	public String toIntroduction(HttpServletRequest request) {
			return "introduction";
	}

	@RequestMapping(value="/getPoster", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public Callable<String> getPoster() {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				
				return  ConstantUtil.DOMAIN_URL + "resources/img/poster/h1.jpg;" +
						ConstantUtil.DOMAIN_URL + "resources/img/poster/h2.jpg;" +
						ConstantUtil.DOMAIN_URL + "resources/img/poster/h3.jpg";
			}
		};
	}
	
	
	@RequestMapping(value="/getCurrentMovie", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public Callable<String> getCurrentMovie() {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				
				return movieService.getCurrentMovie();
			}
		};
	}
	
	@RequestMapping(value="/getLaterMovie", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public Callable<String> getLaterMovie() {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				
				return movieService.getLaterMovie();
			}
		};
	}


	@RequestMapping(value="/getMovieSummary", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public Callable<String> getMovieSummary(HttpServletRequest request) {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				
				String movieIdString = request.getParameter("movieId").trim();
				Integer movieId;
				if(movieIdString.equals("")){
					return "";
				}
				else{
					movieId = Integer.parseInt(movieIdString);
				}
				
				return movieService.getMovieSummary(movieId);
			}
		};
	}
	
	@RequestMapping(value="/getMovieDscription", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public Callable<String> getMovieDscription(HttpServletRequest request) {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				
				String movieIdString = request.getParameter("movieId").trim();
				Integer movieId;
				if(movieIdString.equals("")){
					return "";
				}
				else{
					movieId = Integer.parseInt(movieIdString);
				}
				
				return movieService.getMovieDscription(movieId);
			}
		};
	}
	
}
