package itinerary;

import itinerary.model.Accommodation;
import itinerary.model.Itinerary;
import itinerary.model.Transportation;
import utils.DateValidatorUsingDateFormat;

import java.io.IOException;
import java.util.Scanner;

public class ItineraryView {
    public Transportation getTransportation(){
        Scanner scanner = new Scanner(System.in);
        DateValidatorUsingDateFormat dvudf = new DateValidatorUsingDateFormat("HH:mm");
        try{
            System.out.println("출발지를 입력해주세요.");
            String departurePlace = scanner.nextLine();
            System.out.println("도착지를 입력해주세요.");
            String destination = scanner.nextLine();

            System.out.println("출발 시간을 입력해주세요.(예. 15:00)");
            String departureTime = scanner.nextLine();
            if(!dvudf.isValid(departureTime)){
                throw new IllegalArgumentException("출발 시간이 올바르지 않습니다.");
            }
            System.out.println("도착 시간을 입력해주세요.(예. 11:00)");
            String arrivalTime = scanner.nextLine();
            if(!dvudf.isValid(arrivalTime)){
                throw new IllegalArgumentException("도착시간이 올바르지 않습니다.");
            }
            return new Transportation(departurePlace,destination,departureTime,arrivalTime);
        }catch(IllegalArgumentException error){
            System.out.println("⚠️ 오류: " + error.getMessage());
            return null;        }



    }

    public Accommodation getAccommodation() throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        DateValidatorUsingDateFormat dvudf = new DateValidatorUsingDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            System.out.println("체크인 날짜와 시간을 입력해주세요.(예. 2023-03-04 23:59:59)");
            String checkIn = scanner.nextLine();
            if(!dvudf.isValid(checkIn)){
                throw new IllegalArgumentException("체크인 날짜 형식이 올바르지 않습니다.");
            };
            System.out.println("체크아웃 날짜를 입력해주세요.(예. 2023-03-05 23:59:59)");
            String checkOut = scanner.nextLine();
            if(!dvudf.isValid(checkOut)){
                throw new IllegalArgumentException("체크아웃 날짜 형식이 올바르지 않습니다.");
            }
            return new Accommodation(checkIn.replace(" ", "T"),checkOut.replace(" ", "T"));
        }catch(IllegalArgumentException error){
            System.out.println("⚠️ 오류: " + error.getMessage());
            return null;
        }
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
