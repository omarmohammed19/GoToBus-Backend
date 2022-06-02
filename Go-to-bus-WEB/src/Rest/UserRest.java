package Rest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Models.Trip;
import Models.User;
import Services.UserService;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRest {
	
	//EJB
	@EJB
	UserService userservice;
	
	@GET
	@Path("/getusers")
	public List<User> listAllUsers(){
		return userservice.getAllUsers();
	}

	
	@POST
	@Path("/user")
	public Response createUser(User user) {
		Response.ResponseBuilder builder = null;
		
		try
		{
			userservice.createUser(user);
			builder = Response.ok();
			
		}catch(Exception e)
		{
			Map<String, String> responseObj= new HashMap<String,String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj);
		}
		
		return builder.build();
	}
	
	@POST
	@Path("/login")
	public Response login(User obj) {
		Response.ResponseBuilder builder = null;
		
		try
		{
			if (userservice.login(obj.getUsername(),obj.getPassword()))
			{
				builder = Response.ok();
				return builder.build();
			}
			
			builder = Response.status(Response.Status.BAD_REQUEST);
			return builder.build();
			
		}catch(Exception e)
		{
			Map<String, String> responseObj= new HashMap<String,String>();
			responseObj.put("error", "Login Failed");
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		
		return builder.build();
	}
	
	@GET
	@Path("/viewtrips/{id}")
	public Set<Trip> getUserTrips(@PathParam("id") long id){
		return userservice.getUserTrips(id);
	}
}