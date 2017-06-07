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

import com.movbooking.entity.User;
import com.movbooking.services.UserService;
import com.movbooking.util.ServletUtil;


@Controller
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class.getName());
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String toLoginPage(@CookieValue(value="uid", required=false) String userid, HttpServletRequest request) {
		String ip = ServletUtil.getRemoteAddress(request);
		logger.info(ip + " visit login.html");
		//User user = null;
		/*
		if (userid != null) {
			user = userService.getUserById(Integer.parseInt(userid));
		}
		if (user == null) {
			return "login";
		} else {
			return "welcome";
		}
		*/
		return "login";
	}
	
	@RequestMapping(value="/checkLogin", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Callable<String> checkLogin(HttpServletRequest request, HttpServletResponse response) {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				String username = request.getParameter("username").trim();
				String password = request.getParameter("password").trim();
				User user = new User(username, password);
				User userWithId = userService.userLogin(user);
				String ans=null;
				if (userWithId != null) {
					ans = "1";
					Cookie cookie = new Cookie("uid", userWithId.getId().toString());
					cookie.setPath("/");
					response.addCookie(cookie);
				} else {
					ans = "0";
				}
				return ans;
			}
		};
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Callable<String> register(HttpServletRequest request, HttpServletResponse response) {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				String username = request.getParameter("username").trim();
				String password = request.getParameter("password").trim();
				User user = new User(username, password);
				User registerUser = userService.createNewUser(user);
				String ans=null;
				if (registerUser != null) {
					ans = "1";
					Cookie cookie = new Cookie("uid", registerUser.getId().toString());
					cookie.setPath("/");
					response.addCookie(cookie);
				} else {
					ans = "0";
				}
				return ans;
			}
		};
	}
	
	@RequestMapping(value="/getUserName", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Callable<String> getUserName(HttpServletRequest request, HttpServletResponse response) {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				String userId = request.getParameter("userId").trim();
				User user = userService.getUserById(Integer.parseInt(userId));
				return user.getUserName();
			}
		};
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = null;
		Cookie[] cookies = null;
		cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().compareTo("uid") == 0) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}
	}
	
	
}
