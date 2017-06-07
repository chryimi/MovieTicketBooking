package com.movbooking.entity;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ticket_id")
	private Integer ticketId;
	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="showing_id")
	private Integer showingId;
	
	@Column(name="seat_row")
	private Integer seatRow;
	
	@Column(name="set_col")
	private Integer seatColumn;
	
	@Column(name="buy_time")
	private Calendar time;
	
	


	public Ticket(Integer userId, Integer showingId, Integer seatRow, Integer seatColumn) {
		super();
		this.userId = userId;
		this.showingId = showingId;
		this.seatRow = seatRow;
		this.seatColumn = seatColumn;
		this.time = Calendar.getInstance();
	}


	public Integer getTicketId() {
		return ticketId;
	}


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getShowingId() {
		return showingId;
	}

	public void setShowingId(Integer showingId) {
		this.showingId = showingId;
	}

	public Integer getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(Integer seatRow) {
		this.seatRow = seatRow;
	}

	public Integer getSeatColumn() {
		return seatColumn;
	}

	public void setSeatColumn(Integer seatColumn) {
		this.seatColumn = seatColumn;
	}

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}
	
	
}
