package itinerary.model;

public class Itinerary{
    int itineraryId;
    Transportation transportation;
    Accommodation accommodation;

    public Itinerary(Transportation transportation, Accommodation accommodation){
        this.transportation = transportation;
        this.accommodation = accommodation;
    }

    public void setItineraryId(int n){
        this.itineraryId = n;
    }

    public Transportation getTransportation(){
        return transportation;
    }
    public Accommodation getAccommodation(){
        return accommodation;
    }


}
