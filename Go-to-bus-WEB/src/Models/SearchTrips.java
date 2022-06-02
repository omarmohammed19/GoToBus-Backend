package Models;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SearchTrips implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		//Search
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id ;
	 	
	    private String from_date;
	    private String to_date;
	    private long from_station;
	    private long to_station;
		
	    
	    
	    public SearchTrips() {
			super();
			// TODO Auto-generated constructor stub
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getFrom_date() {
			return from_date;
		}
		public void setFrom_date(String from_date) {
			this.from_date = from_date;
		}
		public String getTo_date() {
			return to_date;
		}
		public void setTo_date(String to_date) {
			this.to_date = to_date;
		}
		public long getFrom_station() {
			return from_station;
		}
		public void setFrom_station(long from_station) {
			this.from_station = from_station;
		}
		public long getTo_station() {
			return to_station;
		}
		public void setTo_station(long to_station) {
			this.to_station = to_station;
		}

		

}
