import itinerary.ItineraryView;
import java.util.Scanner;
import trip.TripService;
import trip.TripView;
import user.User;
import user.UserHandler;
import user.UserService;
import view.LoginView;
import view.MainView;

public class MainController {

  public void run() {
    Scanner scanner = new Scanner(System.in);
    String userBasePath = "data/users";

    UserHandler userHandler = new UserHandler(userBasePath);

    UserService userService = new UserService(userHandler);
    TripService tripService = new TripService(userHandler);

    LoginView loginView = new LoginView(userService, scanner);
    TripView tripView = new TripView(tripService, scanner);
    ItineraryView itineraryView = new ItineraryView();
    MainView mainView = new MainView(scanner, tripView, itineraryView);

    while (true) {
      User loggedInUser = loginView.show();

      if (loggedInUser != null) {
        mainView.show(loggedInUser.getId());
      } else {
        break;
      }
    }
  }
}
