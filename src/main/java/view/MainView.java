package view;

import java.util.Scanner;

public class MainView {
    private final Scanner scanner;

    public MainView() {
        this.scanner = new Scanner(System.in);
    }

    public boolean show() {
        while (true) {
            System.out.println("--- 메인 화면 ---");
            System.out.println("(1) 여행기록, (2) 여정기록, (3) 여행조회, (4) 여정조회, (5) 종료");
            System.out.print("시작할 메뉴번호를 입력하세요. > ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // 여행기록
                    break;
                case "2":
                    // 여정기록
                    break;
                case "3":
                    // 여행조회
                    break;
                case "4":
                    // 여정조회
                    break;
                case "5":
                    System.out.println("프로그램을 종료합니다.");
                    return false;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
