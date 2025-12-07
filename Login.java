import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    private static final String PATIENT_FILE = "patient.csv";
    private static final String STAFF_FILE = "medicalstaff.csv";
    // perform basic login function

    // rcv username, password
    public static Users authenticateData(String username, String password) {

        // 1. Check patient.csv
        Users staff = checkCredentials(STAFF_FILE, username, password);
        if (staff != null) {
            return staff;
        }

        Users patient = checkCredentials(PATIENT_FILE, username, password);
        // 2. Check medicalstaff.csv
        if (patient != null) {
            return patient;
        }

        // Neither file matched
        return null;
    }

    // Helper method to check a CSV file
    private static Users checkCredentials(String filename, String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            // for each line in the file
            while ((line = br.readLine()) != null) {
                // make a scanner for that line
                try (Scanner lineScanner = new Scanner(line);) {
                    // split each line's value based on a comma
                    lineScanner.useDelimiter(",");

                    int id = lineScanner.nextInt();
                    String lineUsername = lineScanner.next();
                    String linePassword = lineScanner.next();
                    String lineName = lineScanner.next();
                    String lineEmail = lineScanner.next();
                    // for patients, it's notes, and for staff it's dept
                    String other = lineScanner.next();

                    if (username.equals(lineUsername) && password.equals(linePassword)) {
                        if (PATIENT_FILE.equals(filename)) {
                            PatientUsers user = new PatientUsers(id, lineUsername);
                            user.setPassword(linePassword);
                            user.setName(lineName);
                            user.setEmail(lineEmail);
                            user.setTreatment_notes(other);
                            return user;
                        }
                        if (STAFF_FILE.equals(filename)) {
                            StaffUsers user = new StaffUsers(id, lineUsername);
                            user.setPassword(linePassword);
                            user.setName(lineName);
                            user.setEmail(lineEmail);
                            user.setDepartment(other);
                            return user;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return null; // No match found
    }

    public static PatientManager setupPatientManager(Users user) {
        return new PatientManager(user);
    }
}
