package Models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Stateless
@LocalBean
@Entity
public class Trip implements Serializable {
	
	//Trip
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String from_station;
	
	private String to_station;
	
	private int available_seats;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date departure_time;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date arrival_time;
	
	private static final long serialVersionUID = 1L;
	
	public Trip() {
		super();
	}
	
	public Trip(String from_station, String to_station, int available_seats, Date departure_time, Date arrival_time,
			Set<User> users) {
		super();
		this.from_station = from_station;
		this.to_station = to_station;
		this.available_seats = available_seats;
		this.departure_time = departure_time;
		this.arrival_time = arrival_time;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	private User u;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFrom_station() {
		return from_station;
	}

	public void setFrom_station(String from_station) {
		this.from_station = from_station;
	}

	public String getTo_station() {
		return to_station;
	}

	public void setTo_station(String to_station) {
		this.to_station = to_station;
	}

	public int getAvailable_seats() {
		return available_seats;
	}

	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}

	public Date getDeparture_time() {
		return departure_time;
	}

	public void setDeparture_time(Date departure_time) {
		this.departure_time = departure_time;
	}

	public Date getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(Date arrival_time) {
		this.arrival_time = arrival_time;
	}
	
	public void setUser(User u) {
		this.u = u;
	}
	
	

}
