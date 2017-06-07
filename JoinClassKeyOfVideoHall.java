package com.movbooking.util;

import java.io.Serializable;

import javax.persistence.Column;
public class JoinClassKeyOfVideoHall implements Serializable{
	private static final long serialVersionUID = 3038003953741008010L;

	@Column(name="cinema_id")
	private Integer cinemaId;
	
	@Column(name="hall_no")
	private Integer hallNo;
	
	public JoinClassKeyOfVideoHall() {}
	
	public JoinClassKeyOfVideoHall(Integer cinemaId, Integer hallNo) {
		super();
		this.cinemaId = cinemaId;
		this.hallNo = hallNo;
	}
	
	public Integer getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(Integer cinemaId) {
		this.cinemaId = cinemaId;
	}
	public Integer getHallNo() {
		return hallNo;
	}
	public void setHallNo(Integer hallNo) {
		this.hallNo = hallNo;
	}
}