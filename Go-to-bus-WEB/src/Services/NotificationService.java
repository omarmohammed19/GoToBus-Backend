package Services;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Models.Notification;
import Models.Trip;
import Models.User;

@Stateless
public class NotificationService {
	
	//Persist
	@PersistenceContext(unitName = "Project")
	private EntityManager em;
	
	@EJB
	UserService userService ;
	
	
	public Set<Notification> getUserNotifications(long id)
	{
		User u1 = userService.getUserById(id);
		return u1.getNotifications();
	}

}
