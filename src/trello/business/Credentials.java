package trello.business;
import java.util.Scanner;

public class Credentials {
    private static String username;
    private static String password;

    private Credentials(String username, String password) {
        Credentials.username = username;
        Credentials.password = password;
    }

    public static Credentials readFromConsole() {
        //hint: put code here to read from console
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        username = scanner.nextLine();
        System.out.println("Enter password: ");
        password = scanner.nextLine();
        return new Credentials(username, password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
