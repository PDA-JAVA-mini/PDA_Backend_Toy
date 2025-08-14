package itinerary.model;

public class Transportation {
    String departurePlace;
    String destination;
    String departureTime;
    String arrivalTime;

    public Transportation(String departurePlace, String destination, String departureTime, String arrivalTime){
        this.departurePlace = departurePlace;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getDeparturePlace(){
        return departurePlace;
    }
    public String getDestination(){
        return destination;
    }
    public String getDepartureTime(){
        return departureTime;
    }
    public String getArrivalTime(){
        return arrivalTime;
    }
}
