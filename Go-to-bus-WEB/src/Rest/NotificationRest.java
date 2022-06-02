package Rest;

import java.util.Set;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Models.Notification;
import Models.Trip;
import Services.NotificationService;
import Services.UserService;

@Path("/notifications")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotificationRest {
	//EJB
	@EJB
	UserService userService;
	
	
	@GET
	@Path("/{id}")
	public Set<Notification> notificationByUserId(@PathParam("id") long id) {
		return userService.getUserNotifications(id);
	}
	

}
