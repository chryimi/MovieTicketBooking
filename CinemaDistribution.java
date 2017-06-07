package com.movbooking.entity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cinema_distribution")
public class CinemaDistribution {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String city;
	
	private String area;
	
	private Integer movieId;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=(CascadeType.ALL))
	@JoinColumn(name="cinemaId")
	private Cinema cinema;
	

	public CinemaDistribution() {
	}
	
	public CinemaDistribution(String city, String area, Integer movieId, Cinema cinema) {
		super();
		this.city = city;
		this.area = area;
		this.movieId = movieId;
		this.cinema = cinema;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public Cinema getCinema() {
		return cinema;
	}


	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	
}
