package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import Models.Station;
import Models.User;

@Stateless
public class StationService {

	//Persist
	@PersistenceContext(unitName = "Project")
	private EntityManager em;
	
	public void createStation(Station station) {
		em.persist(station);
	}
	
	public Station getStationById(long id) {
		Station station = em.find(Station.class, id);
		if(station == null) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return station;
	}
	
	public List<Station> getAllStations(){
		TypedQuery<Station> query = em.createQuery("Select s from Station s",Station.class);
		List<Station> station = query.getResultList();
		return station;
	}
}
