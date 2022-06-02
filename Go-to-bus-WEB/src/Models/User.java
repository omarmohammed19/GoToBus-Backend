package Models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Stateless
@LocalBean
@Entity
public class User implements Serializable{
	//User
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String username;
	
	private String password;
	
	private String full_name;
	
	private String role;
	
	@ManyToMany(mappedBy ="users", fetch = FetchType.EAGER)
	private Set<Trip> trips = new HashSet<Trip>();
	
	private boolean loggedIn;
	
	@OneToMany(mappedBy= "user",fetch= FetchType.EAGER)
	private Set<Notification> notifications = new HashSet<Notification>();
	
	private static final long serialVersionUID = 1L;
	
	public User()
	{
		super();
		this.loggedIn = false;
	}
	public User(String username,String password,String full_name,String role,boolean loggedIn)
	{
		super();
		this.username = username;
		this.password=password;
		this.full_name=full_name;
		this.role=role;
		this.loggedIn=false;
	}

	
	public Set<Notification> getNotifications() {
		return this.notifications;
	}
	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}

	public Set<Trip> getTrips() {
		return trips;
	}
	public void setTrips(Set<Trip> trips) {
		this.trips = trips;
	}


	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public void addTripToUser(Trip trip){
		this.trips.add(trip);
		
	}
	public void addNotification(Notification notification)
	{
		this.notifications.add(notification);	
		System.out.println(notification.getMessage());
	}
}