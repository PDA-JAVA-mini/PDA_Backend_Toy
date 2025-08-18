package view;

import user.User;
import user.UserService;

import java.util.Scanner;

public class LoginView {
    private final UserService userService;
    private final Scanner scanner;

    public LoginView(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    public User show() {
        while (true) {
            System.out.println("--- PDA --- ");
            System.out.println("(1)로그인 (2)회원가입 (3)종료");
            System.out.print("> ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    User loggedInUser = login();
                    if (loggedInUser != null) {
                        return loggedInUser;
                    }
                    break;
                case "2":
                    signup();
                    break;
                case "3":
                    System.out.println("프로그램을 종료합니다.");
                    return null;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private User login() {
        System.out.print("Login ID: ");
        String loginId = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            User user = userService.login(loginId, password);
            System.out.println("로그인 성공!");
            return user;
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void signup() {
        System.out.print("Login ID: ");
        String loginId = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Nickname: ");
        String nickname = scanner.nextLine();

        try {
            userService.signup(loginId, password, nickname);
            System.out.println("회원가입 성공!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
