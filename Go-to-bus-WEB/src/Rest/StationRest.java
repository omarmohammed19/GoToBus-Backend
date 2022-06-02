package Rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

import Models.Station;
import Models.User;
import Services.StationService;


@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StationRest {
	
	//EJB
	@EJB
	StationService stationservices;
	
	@POST
	@Path("/station")
	public Response createStation(Station station) {
		Response.ResponseBuilder builder = null;
		try
		{
			stationservices.createStation(station);
			builder = Response.ok();
			
		}catch(Exception e)
		{
			Map<String, String> responseObj= new HashMap<String,String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj);
		}
		
		return builder.build();
	}
	
	@GET
	@Path("/station/{id}")
	public Station stationById(@javax.ws.rs.PathParam("id") long id) {
		return stationservices.getStationById(id);
	}
	
	@GET
	@Path("/getstations")
	public List<Station> listAllStations(){
		return stationservices.getAllStations();
	}

}
