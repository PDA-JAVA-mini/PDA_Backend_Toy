import itinerary.ItineraryView;
import itinerary.model.Itinerary;

public class MainController {
    public void run(){
        int caseNum = 2;
        switch (caseNum) {
            case 2:
                boolean isFinished = true;
                while(isFinished){
                    ItineraryView itineraryView = new ItineraryView();
                    Itinerary i = new Itinerary(itineraryView.getTransportation(), itineraryView.getAccommodation());
                    itineraryView.showItinerary(i);

                }
                break;


        }
    }
}
