package Rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Models.SearchTrips;
import Models.Station;
import Models.Trip;
import Models.User;
import Models.UserXTrip;
import Services.TripService;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TripRest {
	
	//EJB
	@EJB
	TripService tripservices;
	
	@GET
	@Path("/gettrips")
	public List<Trip> listAlltrips(){
		return tripservices.getAllTrips();
	}
	
	@POST
	@Path("/trips")
	public Response createTrip(Trip trip) {
		Response.ResponseBuilder builder = null;
		if(tripservices.createTrip(trip))
		{
			builder = Response.ok();
		}
		else
		{
			Map<String, String> responseObj= new HashMap<String,String>();
			responseObj.put("error", "No logged in Admin");
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj);
		}
		return builder.build();
	}
	
	@GET
	@Path("/trips/{id}")
	public Trip tripById(@javax.ws.rs.PathParam("id") long id) {
		return tripservices.getTripById(id);
	}

	@PUT
	@Path("/booktrip")
	public Response bookTrip(UserXTrip uXt) {
		
		Response.ResponseBuilder builder = null;

		if(tripservices.bookTrip(uXt)) {
			Map<String, String> responseObj= new HashMap<String,String>();
			responseObj.put("error", "Trip booking successful");
			builder = Response.status(Response.Status.OK).entity(responseObj);
		}
		else {
			Map<String, String> responseObj= new HashMap<String,String>();
			responseObj.put("error", "Trip booking failed");
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj);
		}
			return builder.build();
	}
	
	@POST
	@Path("/searchtrips")
	public Set<Trip> searchTrip(SearchTrips st)
	{
		Response.ResponseBuilder builder = null;
		
		try
		{
			return  tripservices.searchTrips(st);
			//builder = Response.ok();
			
		}catch(Exception e)
		{
			Map<String, String> responseObj= new HashMap<String,String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj);
		}
		return tripservices.searchTrips(st);
	}
}
