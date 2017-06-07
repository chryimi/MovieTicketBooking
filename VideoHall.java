package com.movbooking.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;y
import com.movbooking.util.JoinClassKeyOfVideoHall;

@Entity
@Table(name="video_hall")
public class VideoHall {
	
	@EmbeddedId
	private JoinClassKeyOfVideoHall videoHallKey;
	
	@Column(name="max_column")
	private Integer maxColumn;
	
	@Column(name="max_row")
	private Integer maxRow;
	
	//用'a'表示未售出，'b'表示已售出，'_'表示不是座位,'c'表示待转售
	@Column(name="seat_matrix")
	private String seatMatrix;
	
	public VideoHall() {}
	
	public VideoHall(Integer cinemaId, Integer hallNo, Integer maxRow, Integer maxColumn, String seatMatrix) {
		super();
		this.videoHallKey = new JoinClassKeyOfVideoHall(cinemaId, hallNo);
		this.maxColumn = maxColumn;
		this.maxRow = maxRow;
		this.seatMatrix = seatMatrix;
	}

	public Integer getCinemaId() {
		return videoHallKey.getCinemaId();
	}
	public void setCinemaId(Integer cinemaId) {
		videoHallKey.setCinemaId(cinemaId);
	}
	public Integer getHallNo() {
		return videoHallKey.getHallNo();
	}
	public void setHallNo(Integer hallNo) {
		videoHallKey.setHallNo(hallNo);
	}
	public Integer getMaxColumn() {
		return maxColumn;
	}
	public void setMaxColumn(Integer maxColumn) {
		this.maxColumn = maxColumn;
	}
	public Integer getMaxRow() {
		return maxRow;
	}
	public void setMaxRow(Integer maxRow) {
		this.maxRow = maxRow;
	}

	public String getSeatMatrix() {
		return seatMatrix;
	}

	public void setSeatMatrix(String seatMatrix) {
		this.seatMatrix = seatMatrix;
	}

	@Override
	public String toString() {
		return "VideoHall [videoHallKey=" + videoHallKey + ", maxColumn=" + maxColumn + ", maxRow=" + maxRow
				+ ", seatMatrix=" + seatMatrix + "]";
	}
	
	

}

