package trip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripService {

    private final UserHandler userHandler;

    public TripService(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    public Trip createTrip(int loggedInUserId, String tripName, Date startDate, Date endDate) {
        Trip newTrip = new Trip(tripName, startDate, endDate);
        return userHandler.createTripForUser(loggedInUserId, newTrip);
    }
}
