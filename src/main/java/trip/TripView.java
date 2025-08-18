package trip;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TripView {

    private final TripService tripService;
    private final Scanner scanner;

    public TripView(TripService tripService, Scanner scanner) {
        this.tripService = tripService;
        this.scanner = scanner;
    }

    public void createTrip(int loggedInUserId) {
        System.out.println("--- 새로운 여행 기록 ---");
        try {
            System.out.println("여행 이름을 입력하세요: ");
            String tripName = scanner.nextLine();
            System.out.println("여행 시작일 (YYYY-MM-DD): ");
            Date startDate = new Date(scanner.nextLine());
            System.out.println("여행 종료일 (YYYY-MM-DD): ");
            Date endDate = new Date(scanner.nextLine());

            Trip createdTrip = tripService.createTrip(loggedInUserId, tripName, startDate, endDate);
            System.out.println("여행이 성공적으로 기록되었습니다! (ID: " + createdTrip.getTripId() + ")");
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다.: " + e.getMessage());
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
