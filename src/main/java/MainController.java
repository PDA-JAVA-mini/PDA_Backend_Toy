import user.UserHandler;
import user.UserService;
import view.LoginView;

public class MainController {
    public void run(){
        UserHandler userHandler = new UserHandler();
        UserService userService = new UserService(userHandler);
        LoginView loginView = new LoginView(userService);

        loginView.show();
    }
}
