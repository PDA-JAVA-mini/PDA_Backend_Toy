package trip;

import java.util.Date;
import java.util.List;
import user.UserHandler;

public class TripService {

    private final UserHandler userHandler;

    public TripService(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    public Trip createTrip(int loggedInUserId, String tripName, Date startDate, Date endDate) {
        Trip newTrip = new Trip(tripName, startDate, endDate);
        return userHandler.createTripForUser(loggedInUserId, newTrip);
    }

    public List<Trip> getAllTripsForUser(int loggedInUserId) {
        return userHandler.findAllTripsForUser(loggedInUserId);
    }
}
