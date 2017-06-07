package com.movbooking.entity;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.movbooking.util.ConstantUtil;

@Entity
@Table(name="movie")
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="movie_id")
	private Integer movieId;
	
	@Column(name="name")
	private String name;  //电影名字
	
	@Column(name="director")
	private String director;  //导演
	
	@Column(name="main_actor")
	private String mainActor;  //主要演员
	
	@Column(name="showing_type")
	private String showingType;  //3:3d or 2:2d
	
	@Column(name="minutes")
	private int minutes;  //电影时长
	
	@Column(name="release_date")
	private Calendar releaseDate;  //电影首映日期
	
	@Column(name="movie_description", columnDefinition="TEXT")
	private String movieDescription;  //电影简介，存于/data/movieInfo/movieDescription/{movieId.txt}
	
	public Movie() {}
	
	public Movie(String name, String director, String mainActor, String showingType, int minutes, Calendar releaseDate,
			String movieDescription) {
		super();
		this.name = name;
		this.director = director;
		this.mainActor = mainActor;
		this.showingType = showingType;
		this.minutes = minutes;
		this.releaseDate = releaseDate;
		this.movieDescription = movieDescription;
	}


	public Integer getMovieId() {
		return movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getMainActor() {
		return mainActor;
	}

	public void setMainActor(String mainActor) {
		this.mainActor = mainActor;
	}

	public String getShowingType() {
		return showingType;
	}

	public void setShowingType(String showingType) {
		this.showingType = showingType;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public Calendar getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getMovieDescription() {
		return movieDescription;
	}


	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

	
	/*存放电影预览图片位置
	 *"/resources/img/movieInfo/movieImg/{name.jpg}"
	 * */
	public String computeMovieImgPath() {
		String resPath = "resources/img/movieImg/" + this.movieId + ".jpg";
		return ConstantUtil.DOMAIN_URL + resPath;
	}
	
	
	/*电影简介
	 *从"/data/movieInfo/movieDescription/{name.txt}"读取
	 * */
	/*
	public String readMovieDescription(String fileName) {
		String resPath = "../../data/movieInfo/movieDescription/"+ fileName + ".txt";
		try {
			return FileIOUtil.read(resPath);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	*/

	@Override
	public String toString() {
		return "Movie {movieId=" + movieId + ", name=" + name + ", director=" + director + ", mainActor=" + mainActor
				+ ", showingType=" + showingType + ", minutes=" + minutes + ", releaseDate=" + releaseDate
				+ ", movieDescription=" + movieDescription 
				+ ", movieImgPath=" + computeMovieImgPath() + "}";
	}
	
}
