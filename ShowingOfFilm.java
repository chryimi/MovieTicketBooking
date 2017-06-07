package com.movbooking.entity;

import java.util.Calendar;
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
@Table(name="showing_of_film")
public class ShowingOfFilm {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="showing_id")
	private Integer showingId;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=(CascadeType.ALL))
	@JoinColumn(name="movie_id")
	private Movie movie;
	
	@Column(name="cinema_id")
	private Integer cinemaId;
	
	@Column(name="screen_time")
	private Calendar screenTime;
	
	@Column(name="end_time")
	private Calendar endTime;
	
	@Column(name="video_hall_no")
	private Integer videoHallNo;
	
	@Column(name="language")
	private String language;
	
	@Column(name="price")
	private float price;
	
	@Column(name="showing_type")
	private int showingType;  // 3:3d or 2:2d
	
	@Column(name="seat_matrix")
	private String seatMatrix;
	
	@Column(name="max_row")
	private int maxRow;
	
	@Column(name="max_col")
	private int maxCol;
	
	public ShowingOfFilm() {}
	
	public ShowingOfFilm(Movie movie, Integer cinemaId, Calendar screenTime, Calendar endTime, Integer videoHallNo, String language,
			float price, int showingType) {
		super();
		this.movie = movie;
		this.cinemaId = cinemaId;
		this.screenTime = screenTime;
		this.videoHallNo = videoHallNo;
		this.language = language;
		this.price = price;
		this.showingType = showingType;
		this.endTime = endTime;
	}

	public String getSeatMatrix() {
		return seatMatrix;
	}

	public void setSeatMatrix(String seatMatrix) {
		this.seatMatrix = seatMatrix;
	}
	
	/*
	 * type:0---not occupy; 1---been bought; 2---not a seat*/
	public boolean setSeatMatrix(int row, int col, char type) {
		StringBuffer seatMatrixBuffer = new StringBuffer(seatMatrix);
		if (row >= 0 && row < maxRow && col >= 0 && col < maxCol) {
			seatMatrixBuffer.setCharAt(row*maxCol+col, type);
			this.seatMatrix = seatMatrixBuffer.toString();
			return true;
		} else {
			return false;
		}
	}
	
	public boolean bookSeat(int row, int col) {
		//System.out.println("in showing of film: bookseat:---row:" + row + ", col:" + col);
		//System.out.println("maxrow: " + maxRow+ ", maxCol: " + maxCol);
		if (!(row >= 0 && row < maxRow && col >= 0 && col < maxCol)) {
			return false;
		} else {
			StringBuffer seatMatrixBuffer = new StringBuffer(seatMatrix);
			if (seatMatrixBuffer.charAt(row*maxCol+col) == 'a') {
				seatMatrixBuffer.setCharAt(row*maxCol+col, 'b');
				this.seatMatrix = seatMatrixBuffer.toString();
				System.out.println("new seatMatrix: " + seatMatrix);
				return true;
			} else {
				return false;
			}
		}
	}
	
	

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public int getMaxRow() {
		return maxRow;
	}
	
	public int getMaxCol() {
		return maxCol;
	}


	public Integer getShowingId() {
		return showingId;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Integer getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(Integer cinemaId) {
		this.cinemaId = cinemaId;
	}

	public Calendar getScreenTime() {
		return screenTime;
	}

	public void setScreenTime(Calendar screenTime) {
		this.screenTime = screenTime;
	}

	public Integer getVideoHallNo() {
		return videoHallNo;
	}

	public void setVideoHallNo(Integer videoHallNo) {
		this.videoHallNo = videoHallNo;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getShowingType() {
		return showingType;
	}

	public void setShowingType(int showingType) {
		this.showingType = showingType;
	}
	
	
	
	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}

	public void setMaxCol(int maxCol) {
		this.maxCol = maxCol;
	}

	public String getFormattedSeat() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < maxRow; i++) {
			sb.append("\"");
			for (int j = 0; j < maxCol; j++) {
				sb.append(seatMatrix.charAt(i*maxCol+j));
			}
			sb.append("\"");
			if (i != maxRow - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	/*
	public void calcMaxColAndRow() {
		VideoHallDAO videoHallDAO = new VideoHallDaoImpl();
		JoinClassKeyOfVideoHall key = new JoinClassKeyOfVideoHall(cinemaId, videoHallNo);
		VideoHall videoHall = videoHallDAO.getVideoHall(key);
		this.maxRow = videoHall.getMaxRow();
		this.maxCol = videoHall.getMaxColumn();
		this.seatMatrix = new StringBuffer(videoHall.getSeatMatrix());
	}
	*/
	
	@Override
	public String toString() {
		return "ShowingOfFilm [showingId=" + showingId + ", movie=" + movie + ", cinemaId=" + cinemaId + ", screenTime="
				+ screenTime + ", endTime=" + endTime + ", videoHallNo=" + videoHallNo + ", language=" + language
				+ ", price=" + price + ", showingType=" + showingType + ", seatMatrix=" + seatMatrix + ", maxRow="
				+ maxRow + ", maxCol=" + maxCol + "]";
	}	
	
	
}
