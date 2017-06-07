package com.movbooking.dao;

import java.util.List;

import com.movbooking.entity.Ticket;

public interface TicktetDAO {
	public void addTicket(Ticket ticket);
	public void deleteTicket(Integer ticketId);
	public Ticket getTicket(Integer ticketId);
	public void updateTicket(Ticket ticket);
	public List<Ticket> getTickets();
}

