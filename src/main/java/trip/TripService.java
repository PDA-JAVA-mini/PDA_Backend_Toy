package trip;

import java.util.Date;

public class TripService {

    public final TripHandler tripHandler;

    public TripService(TripHandler tripHandler) {
        this.tripHandler = tripHandler;
    }

    public Trip createTrip(String tripName, Date start_date, Date end_date) {
        Trip newTrip = new Trip(tripName, start_date, end_date);
        return tripHandler.save(newTrip);
    }
}
