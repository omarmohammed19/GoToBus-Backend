package Services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import Models.Notification;
import Models.SearchTrips;
import Models.Station;
import Models.Trip;
import Models.User;
import Models.UserXTrip;

@Stateless
public class TripService {
	
	//Persist
	@PersistenceContext(unitName = "Project")
	private EntityManager em;
	
	@EJB
	private UserService userservices;
	
	@EJB
	private StationService stationService = new StationService();
	
	@EJB
	private Trip trip;
	
	@Inject
	private User user;
	
	public boolean createTrip(Trip trip) {
		TypedQuery<User> query = em.createQuery("Select u from User u where u.loggedIn=true and u.role='admin'",User.class);
		List<User> user = query.getResultList();
	if(user == null || user.isEmpty()) {
			return false;
		}
		else {
			em.persist(trip);
			return true;
		}
	}
	
	public List<Trip> getAllTrips(){
		TypedQuery<Trip> query = em.createQuery("Select t from Trip t",Trip.class);
		List<Trip> trips = query.getResultList();
		return trips;
	}
	
	public List<Trip> listAllTrips(String departure_time , String arrival_time){
		TypedQuery<Trip> query = em.createQuery("Select t from Trip t where FORMATDATETIME(t.departure_time,'yyyy/MM/dd')=?1 or FORMATDATETIME(t.arrival_time,'yyyy/MM/dd')=?2"
				,Trip.class);
		query.setParameter(1, departure_time);
		query.setParameter(2, arrival_time);
		List<Trip> trips = query.getResultList();
		return trips;
	}
	
	public Trip getTripById(long id) {
		Trip trip = em.find(Trip.class, id);
		if(trip == null) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return trip;
	}
	
	public boolean bookTrip(UserXTrip uXt) {
		
		User user = userservices.getUserById(uXt.getUser_id());
		Trip trip = getTripById(uXt.getTrip_id());
		
		if (user.isLoggedIn())
		{
			System.out.println(user.isLoggedIn());
			if(trip.getAvailable_seats() != 0)
			{
				Date dt = new Date();
				Notification notification = new Notification(" You have booked trip from " +trip.getFrom_station() + " to " +trip.getTo_station()+ " successfully",dt);
				notification.setUser(user);
				user.addNotification(notification);
				trip.registerTotrip(user);
				user.addTripToUser(trip);
				trip.setAvailable_seats(trip.getAvailable_seats()-1);
				em.merge(user);
				em.persist(notification);
				em.merge(trip);
				return true;
			}
			else
			{
				Date dt = new Date();
				Notification notification = new Notification(" Sorry, Trip " +trip.getFrom_station() +  " to " + trip.getTo_station() + " have no available seats",dt);
				notification.setUser(user);
				user.addNotification(notification);
				em.merge(user);
				em.persist(notification);
				return false;
			}
			
		}	
		return false;
	}
	public Set<Trip> searchTrips(SearchTrips st)
	{
		Station station = stationService.getStationById(st.getFrom_station());
		Station station2 = stationService.getStationById(st.getTo_station());
		TypedQuery<Trip> query = em.createQuery("Select t from Trip t where t.from_station =?1 and t.to_station =?2 and FORMATDATETIME(t.departure_time,'dd/MM/yyyy') >= ?3 and FORMATDATETIME(t.arrival_time,'dd/MM/yyyy') <= ?4", Trip.class);
		query.setParameter(1, station.getName());
		query.setParameter(2, station2.getName());
		query.setParameter(3, st.getFrom_date());
		query.setParameter(4, st.getTo_date());
		return new HashSet<Trip> (query.getResultList());
	}
	

}
