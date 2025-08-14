package itinerary.model;

public class Accommodation {
    String checkIn;
    String checkOut;

    public Accommodation(String checkIn, String checkOut){
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getCheckIn(){
        return checkIn;
    }
    public String getCheckOut(){
        return checkOut;
    }
}
