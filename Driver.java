import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Driver {

    private static final String CSV_FILE_PATH = "patient.csv";

    public static void main(String[] args) {
        boolean isLoggedIn = false;
        Scanner scanner = new Scanner(System.in);

        Users loggedInUser = null;
        while (loggedInUser == null) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.println("attempting login");
            loggedInUser = Login.authenticateData(username, password);
            if(loggedInUser == null){
                System.out.println("Invalid creds, try again");
            }
        }

        System.out.println("you're logged on, back in main");

        // if (userType == null) {
        // System.out.println("Invalid login.");
        // } else if (userType.equals("PATIENT")) {
        // PatientUsers patientUser = new PatientUsers(); // instantiate patient user
        // object
        // patientUser.printUserInfo();
        // } else if (userType.equals("STAFF")) {
        // new StaffUsers(); // same idea
        // }

        scanner.close();
    }
    // instantiate/call/execute Login

    // upon successful login, patient manager object is created to help
    // perform tasks

    // user interaction. pop-up menus/prompts
}
