import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

    private static final String CSV_FILE_PATH = "patient.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Users loggedInUser = null;
        while (loggedInUser == null) {
            // staff creds
            String username = "sstrange";
            String password = "meditationmagic";

            // // patient creds
            // String username = "sallyw";
            // String password = "password123";

            // // good ol login
            // System.out.print("Enter username: ");
            // String username = scanner.nextLine();
            // System.out.print("Enter password: ");
            // String password = scanner.nextLine();

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
            System.out.println("1 - View Profile");
            if (patientManager.isStaff()) {
                System.out.println("2 - View Patient Information");
            }
            System.out.println("3 - Edit currently viewed patient");
            if (patientManager.isStaff()) {
                System.out.println("4 - Reports");
            }
            String command = scanner.nextLine();
            // the commands
            final String EXIT = "0";
            final String VIEW_PROFILE = "1";
            final String VIEW_PATIENT_INFO = "2";
            final String EDIT_PATIENT_INFO = "3";
            final String REPORTS = "4";
            switch (command) {
                case EXIT:
                    System.out.println("Exiting");
                    exit = true;
                    break;
                case VIEW_PROFILE:
                    patientManager.viewProfile();
                    break;
                case VIEW_PATIENT_INFO:
                    System.out.println("Enter patient ID");
                    String patientId = scanner.nextLine();
                    try {
                        patientManager.viewPatientInfo(patientId);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case EDIT_PATIENT_INFO:
                    if (patientManager.getCurrent() == null) {
                        System.out.println("No patient selected");
                        break;
                    }
                    String attribute = "";
                    boolean isValidAttribute = false;
                    while (!isValidAttribute) {
                        System.out.println("Enter attribute to edit:");
                        attribute = scanner.nextLine();
                        if (!validatePatientAttribute(attribute)) {
                            System.out.println("Invalid attribute");
                        } else {
                            isValidAttribute = true;
                        }
                    }
                    System.out.println("Enter new value:");
                    String newValue = scanner.nextLine();
                    try {
                        patientManager.editProfile(attribute, newValue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case REPORTS:
                    System.out.println("Enter file name for reports:");
                    String fileName = scanner.nextLine();
                    String reportType = "";
                    boolean isValidReport = false;
                    while (!isValidReport) {
                        System.out.println("Enter report type:");
                        System.out.println("A - Patient list by ID");
                        System.out.println("B - Patient list by name");
                        System.out.println("C - Patient email list");
                        System.out.println("D - User info");
                        reportType = scanner.nextLine();
                        if (!validateReportType(reportType)) {
                            System.out.println("Invalid attribute");
                        } else {
                            isValidReport = true;
                        }
                    }
                    patientManager.printReport(fileName, reportType);
                    break;
                default:
                    System.out.println("Bad command, please try again");
                    break;
            }
        }

        scanner.close();
    }

    private static boolean validateReportType(String reportType) {
        ArrayList<String> attributes = new ArrayList<>();
        attributes.add("A");
        attributes.add("B");
        attributes.add("C");
        attributes.add("D");
        for (String attribute : attributes) {
            if (attribute.equals(reportType)) {
                return true;
            }
        }
        return false;
    }

    private static boolean validatePatientAttribute(String attribute) {
        ArrayList<String> attributes = new ArrayList<>();
        attributes.add("password");
        attributes.add("name");
        attributes.add("email");
        attributes.add("treatment_notes");
        for (String patientAttribute : attributes) {
            if (patientAttribute.equals(attribute)) {
                return true;
            }
        }
        return false;
    }
}
