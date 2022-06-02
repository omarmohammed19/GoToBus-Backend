package Services;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import Models.Notification;
import Models.Station;
import Models.User;
import Models.Trip;

@Stateless
public class UserService {

	//Persist
	@PersistenceContext(unitName = "Project")
	private EntityManager em;
	
	public List<User> getAllUsers(){
		TypedQuery<User> query = em.createQuery("Select u from User u",User.class);
		List<User> users = query.getResultList();
		return users;
	}
	
	public void createUser(User user) {
		em.persist(user);
		
	}
	
	public User getUserById(long id) {
		User user = em.find(User.class, id);
		if(user == null) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return user;
	}
	
	public boolean login(String userName , String password)
	{
		TypedQuery<User> query = em.createQuery("Select u from User u Where u.username =:ayhaga",User.class);
		query.setParameter("ayhaga",userName);
		User x = query.getSingleResult();
		if(x.getUsername().equals(userName))
		{
			if(x.getPassword().equals(password))
			{
				x.setLoggedIn(true);
				return true;
			}	
			return false;
		}
		return false;

	}
	
	
	public Set<Trip> getUserTrips(long id){
		User u  = getUserById(id);
		return u.getTrips();
	}
	
	public Set<Notification> getUserNotifications(long id)
	{
		User u1 = getUserById(id);
		return u1.getNotifications();
	}
	
	
}