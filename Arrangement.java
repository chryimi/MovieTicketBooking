package com.movbooking.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="arrangement")
public class Arrangement {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="arrangement_id")
	private Integer arrangementId;
	
	private Integer movieId;
	
	private Integer cinemaId;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=(CascadeType.ALL))
	@JoinColumn(name="showing_id")
	private ShowingOfFilm showing;
	
	
	public Arrangement() {
	}
	
	public Arrangement(Integer movieId, Integer cinemaId, ShowingOfFilm showing) {
		this.movieId = movieId;
		this.cinemaId = cinemaId;
		this.showing = showing;
	}

	
	
	public Integer getArrangementId() {
		return arrangementId;
	}



	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public Integer getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(Integer cinemaId) {
		this.cinemaId = cinemaId;
	}

	public ShowingOfFilm getShowing() {
		return showing;
	}

	public void setShowing(ShowingOfFilm showing) {
		this.showing = showing;
	}

}
