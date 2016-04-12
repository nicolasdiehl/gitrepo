package ch.makery.address.model;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

	@XmlRootElement(name = "vehicles")
public class VehicleListWrapper {
		private List<Vehicle> vehicles;

	    @XmlElement(name = "vehicle")
	    public List<Vehicle> getVehicles() {
	        return vehicles;
	    }

	    public void setVehicles(List<Vehicle> vehicles) {
	        this.vehicles = vehicles;
	    }
}
