package itinerary;

import itinerary.model.Accommodation;
import itinerary.model.Itinerary;
import itinerary.model.Transportation;

import java.util.Scanner;

public class ItineraryView {
    public Transportation getTransportation(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("출발지를 입력해주세요.");
        String departurePlace = scanner.nextLine();
        System.out.println("도착지를 입력해주세요.");
        String destination = scanner.nextLine();
        System.out.println("출발 시간을 입력해주세요.(예. 15:00)");
        String departureTime = scanner.nextLine();
        System.out.println("도착 시간을 입력해주세요.(예. 11:00)");
        String arrivalTime = scanner.nextLine();
        return new Transportation(departurePlace,destination,departureTime,arrivalTime);

    }

    public Accommodation getAccommodation(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("체크인 날짜를 입력해주세요.(예. 2023-03-04)");
        String checkIn = scanner.nextLine();
        System.out.println("체크인 시간을 입력해주세요.(예. 15:00)");
        checkIn = checkIn + "T" + scanner.nextLine() + ":00";
        System.out.println("체크아웃 날짜를 입력해주세요.(예. 2023-03-05)");
        String checkOut = scanner.nextLine();
        System.out.println("체크인 시간을 입력해주세요.(예. 11:00)");
        checkOut = checkOut + "T" + scanner.nextLine() + ":00";
        return new Accommodation(checkIn,checkOut);
    }

    public void showItinerary(Itinerary itinerary){
        System.out.println("["+itinerary.getTransportation().getDeparturePlace() +
                "-"+ itinerary.getTransportation().getDestination() + " 여정 정보]");
        System.out.println("출발 시간: " + itinerary.getTransportation().getDepartureTime());
        System.out.println("도착 시간: " + itinerary.getTransportation().getArrivalTime());
        System.out.println("체크인 시간: " + itinerary.getAccommodation().getCheckIn());
        System.out.println("체크아웃 시간: " + itinerary.getAccommodation().getCheckOut());
        System.out.println();
    }
}
