import user.UserHandler;
import user.UserService;
import view.LoginView;
import view.MainView;

public class MainController {

  public void run() {
    UserHandler userHandler = new UserHandler("src/main/java/data/users");
    UserService userService = new UserService(userHandler);
    LoginView loginView = new LoginView(userService);
    MainView mainView = new MainView();

    if (loginView.show()) {
      mainView.show();
    }
  }
}
