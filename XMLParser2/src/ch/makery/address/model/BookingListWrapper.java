package ch.makery.address.model;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

	@XmlRootElement(name = "Bookings")
public class BookingListWrapper {
		private List<Booking> Bookings;

	    @XmlElement(name = "Booking")
	    public List<Booking> getBookings() {
	        return Bookings;
	    }

	    public void setBookings(List<Booking> Bookings) {
	        this.Bookings = Bookings;
	    }
}
