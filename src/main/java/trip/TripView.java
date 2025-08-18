package trip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException; // import 추가
import java.text.SimpleDateFormat; // import 추가

public class TripView {

    private final TripService tripService;
    private final Scanner scanner;

    public TripView(TripService tripService, Scanner scanner) {
        this.tripService = tripService;
        this.scanner = scanner;
    }

    // TripView.java

    // ...
    public void createTrip(int loggedInUserId) {
        System.out.println("--- 새로운 여행 기록 ---");
        try {
            System.out.print("여행 이름을 입력하세요: ");
            String tripName = scanner.nextLine();

            // 날짜 변환을 위한 포맷터 생성
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            System.out.print("여행 시작일 (YYYY-MM-DD): ");
            Date startDate = formatter.parse(scanner.nextLine());

            System.out.print("여행 종료일 (YYYY-MM-DD): ");
            Date endDate = formatter.parse(scanner.nextLine());

            Trip createdTrip = tripService.createTrip(loggedInUserId, tripName, startDate, endDate);
            System.out.println("🎉 여행이 성공적으로 기록되었습니다! (ID: " + createdTrip.getTripId() + ")");

        } catch (ParseException e) {
            System.out.println("🚨 잘못된 날짜 형식입니다. YYYY-MM-DD 형식으로 입력해주세요.");
        } catch (Exception e) {
            System.out.println("🚨 오류가 발생했습니다: " + e.getMessage());
        }
    }

    public void viewAllTrips(int loggedInUserId) {
        System.out.println("--- 내 여행 목록 ---");
        List<Trip> myTrips = tripService.getAllTripsForUser(loggedInUserId);

        if (myTrips.isEmpty()) {
            System.out.println("기록된 여행이 없습니다.");
        } else {
            myTrips.forEach(trip -> {
                System.out.printf("[%d] %s (%s~%s)\n",
                        trip.getTripId(),
                        trip.getTripName(),
                        trip.getStartDate().toString(),
                        trip.getEndDate().toString());
            });
        }
        System.out.println("-------------------");
    }
}
