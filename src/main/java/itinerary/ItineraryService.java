package itinerary;

import itinerary.model.Accommodation;
import itinerary.model.Itinerary;
import itinerary.model.Transportation;
import trip.Trip;

import java.io.IOException;

public class ItineraryService {
    public final ItineraryHandler itineraryHandler;

    public ItineraryService(ItineraryHandler itineraryHandler){
        this.itineraryHandler = itineraryHandler;
    }

    public void createItinerary(String filePath, Transportation transportation, Accommodation accommodation){
        Trip trip = itineraryHandler.read(Trip.class,filePath);
        Itinerary newItinerary = new Itinerary(transportation, accommodation);
        trip.addItinerary(newItinerary);
        itineraryHandler.write(trip,filePath);
    }
}
