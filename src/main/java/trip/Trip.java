package trip;

import itinerary.model.Itinerary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Trip {
    private int tripId;
    private int userId;
    private String tripName;
    private Date startDate;
    private Date endDate;
    private List<Itinerary> itineraries;

    public Trip() {
    }

    public Trip(String tripName, Date startDate, Date endDate){
        this.tripId = 0;
        this.tripName = tripName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.itineraries = new ArrayList<>();
    }

  public Trip(int tripId, int userId, String tripName, Date startDate,
      Date endDate, List<Itinerary> itineraries) {
    this.tripId = tripId;
    this.userId = userId;
    this.tripName = tripName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.itineraries = itineraries;
  }

  public Trip(int id, String tripName, Date startDate, Date endDate, List<Itinerary> itineraries) {

        this.tripId = id;
        this.tripName = tripName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.itineraries = new ArrayList<>(itineraries);
    }

    public void addItinerary(Itinerary itinerary){
        itineraries.add(itinerary);
    }

    public Trip withId(int id){
        return new Trip(id, this.tripName, this.startDate, this.endDate, this.itineraries);
    }

    public Trip withIdAndUserId(int tripId, int userId) {
        return new Trip(tripId, userId, this.tripName, this.startDate, this.endDate, this.itineraries);
    }

    public int getTripId() {
        return tripId;
    }

    public String getTripName() {
        return tripName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }


}
