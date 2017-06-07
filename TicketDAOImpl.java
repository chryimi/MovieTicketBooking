package com.movbooking.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movbooking.dao.TicktetDAO;

import com.movbooking.entity.Ticket;

@Repository
public class TicketDAOImpl implements TicktetDAO {

	@Autowired
	private SessionFactory sessionfactory;
	
	private Session getCurrentSession() {
		return sessionfactory.getCurrentSession();
	}
	
	@Override
	public void addTicket(Ticket ticket) {
		getCurrentSession().save(ticket);
	}

	@Override
	public void deleteTicket(Integer ticketId) {
		Ticket ticket = getTicket(ticketId);
		if (ticket != null) {
			getCurrentSession().delete(ticket);
		}

	}

	@Override
	public Ticket getTicket(Integer ticketId) {
		Ticket ticket = null;
		ticket = (Ticket)getCurrentSession().get(Ticket.class, ticketId);
		return ticket;
	}

	@Override
	public void updateTicket(Ticket ticket) {
		getCurrentSession().update(ticket);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getTickets() {
		return getCurrentSession().createQuery("from Ticket").list();
	}

}

