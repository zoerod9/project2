import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login {
    private static final String CSV_FILE_PATH = "patient.csv";
    private static final String STAFF_FILE = "medicalstaff.csv";
    // perform basic login function

    // rcv username, password
    public static String authenticateData(String username, String password) {

        // 1. Check patient.csv
        if (checkCredentials(CSV_FILE_PATH, username, password)) {
            return "PATIENT";
        }

        // 2. Check medicalstaff.csv
        if (checkCredentials(STAFF_FILE, username, password)) {
            return "STAFF";
        }

        // Neither file matched
        return null;
    }

    // Helper method to check a CSV file
    private static boolean checkCredentials(String filename, String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Assuming CSV format: username,password,...
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String fileUser = parts[1].trim();
                    String filePass = parts[2].trim();

                    if (fileUser.equals(username) && filePass.equals(password)) {
                        return true;
                    }
                }
            }
            // print error message if not, ask user to re-enter
            } catch (IOException e) {
                System.err.println("Error reading CSV file: " + e.getMessage());
            }
            return false; // No match found
        }
    }
    
    // upon successful login, set up and return patient manager

