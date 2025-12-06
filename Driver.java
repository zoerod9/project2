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
            loggedInUser = Login.authenticateData(username, password);
            if (loggedInUser == null) {
                System.out.println("Invalid creds, try again");
            }
        }

        System.out.println("Welcome, " + loggedInUser.getName());
        PatientManager patientManager = Login.setupPatientManager(loggedInUser);

        // interact with users to ask which functions to perform
        boolean exit = false;
        while (!exit) {
            System.out.println("Please enter a command:");
            System.out.println("0 - Exit");
            String command = scanner.next();
            // the commands
            final String EXIT = "0";
            switch (command) {
                case EXIT:
                    System.out.println("Exiting");
                    exit = true;
                    break;
                
                default:
                    System.out.println("Bad command, please try again");
                    break;
            }
        }

        scanner.close();
    }
}
