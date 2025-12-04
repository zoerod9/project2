import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Driver {

    private static final String CSV_FILE_PATH = "patient.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        String userType = Login.authenticateData(username, password);

        if (userType == null) {
            System.out.println("Invalid login.");
        } else if (userType.equals("PATIENT")) {
            new PatientUsers(); // or call the method that opens that menu
        } else if (userType.equals("STAFF")) {
            new StaffUsers(); // same idea
        }

        scanner.close();
    }
    // instantiate/call/execute Login

    // upon successful login, patient manager object is created to help
    // perform tasks

    // user interaction. pop-up menus/prompts
}
