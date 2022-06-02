package Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Stateless
@LocalBean
@Entity
public class Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Notify
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	private String message;
	
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notification( String message, Date notification_datetime) {
		super();
		this.message = message;
		
		this.notification_datetime = new Date();
	}

	private Date notification_datetime ; 

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Date getNotification_datetime() {
		return notification_datetime;
	}

	public void setNotification_datetime(Date notification_datetime) {
		this.notification_datetime = notification_datetime;
	}

}
