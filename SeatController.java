package com.movbooking.controller;


import java.util.concurrent.Callable;

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
import com.movbooking.services.ChooseSeatService;
import com.movbooking.services.UserService;
import com.movbooking.util.ServletUtil;

@Controller
public class SeatController {
	private static Logger logger = Logger.getLogger(UserController.class.getName());
	
	@Autowired
	ChooseSeatService chooseSeatService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/bookSeat", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String getBookSeatPage(@CookieValue(value="uid", required=false) String userid, HttpServletRequest request) {
		String ip = ServletUtil.getRemoteAddress(request);
		logger.info(ip + " selectSeat.html");
		return "selectSeats";
	}
	
	@RequestMapping(value="/bookSeat/showingInfo", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Callable<String> getShowingInfo(HttpServletRequest request, HttpServletResponse response) {
		return new Callable<String>() {
			
			@Override
			public String call() throws Exception {
				response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				
				String showingID = request.getParameter("showingid").trim();
				return chooseSeatService.getShowingInfo(Integer.parseInt(showingID));
			}
		};
	}
	
	@RequestMapping(value="/bookSeat/book", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Callable<String> bookTicket(@CookieValue(value="uid", required=false) String userid, HttpServletRequest request, HttpServletResponse response) {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("userid is " + userid);
				
				if (userid == null || "".equals(userid)) {
					return "NOT LOGIN, Please login first";
				}
				User user = userService.getUserById(Integer.parseInt(userid));
				if (user == null) {
					return "NOT LOGIN, Please login first";
				} else {
					String showingID = request.getParameter("showingId").trim();
					int ticketNum = Integer.parseInt(request.getParameter("ticketNumber").trim());
					String ticketStr = request.getParameter("tickets");
					System.out.println(ticketStr);
					String[] tickets = ticketStr.split(",");
					boolean isSuccess = true;
					for (int i = 0; i < ticketNum; i++) {
						int rows = Integer.parseInt(tickets[i].split("_")[0]);
						int cols = Integer.parseInt(tickets[i].split("_")[1]);
						isSuccess = chooseSeatService.bookSeat(rows, cols, Integer.parseInt(showingID), Integer.parseInt(userid));
						if (!isSuccess) {
							break;
						}
					}
					if (isSuccess) {
						return "SUCCESS";
					} else {
						return "FAIL";
					}
				}
			}
			
		};
	}
	

}
