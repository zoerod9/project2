import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PatientManager {

    private static final String PATIENT_FILE = "patient.csv";

    private Users loggedInUser;
    private ArrayList<PatientUsers> patients;
    private PatientUsers current;

    public PatientManager(Users user) {
        this.loggedInUser = user;
        this.patients = getPatients();
        sortPatientsById();
        if (user instanceof PatientUsers) {
            for (PatientUsers patientUser : patients) {
                if (patientUser.getId() == user.getId()) {
                    this.current = patientUser;
                }
            }
        }
    }

    // return the list of patients
    private static ArrayList<PatientUsers> getPatients() {
        ArrayList<PatientUsers> patients = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATIENT_FILE))) {
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
                    String notes = lineScanner.next();

                    // create a patient per line
                    PatientUsers patient = new PatientUsers(id, lineUsername);
                    patient.setPassword(linePassword);
                    patient.setName(lineName);
                    patient.setEmail(lineEmail);
                    patient.setTreatment_notes(notes);

                    // add it to the list
                    patients.add(patient);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return patients;
    }

    public void viewProfile() {
        System.out.println("Viewing user profile");
        loggedInUser.printInfo();
    }

    public boolean isStaff() {
        return loggedInUser instanceof StaffUsers;
    }

    public boolean isPatient() {
        return loggedInUser instanceof PatientUsers;
    }

    public void editProfile(String attribute, String newValue) throws Exception {
        switch (attribute) {
            case "name":
                current.setName(newValue);
                break;
            case "password":
                current.setPassword(newValue);
                break;
            case "email":
                current.setEmail(newValue);
                break;
            case "treatment_notes":
                if (isStaff()) {
                    current.setTreatment_notes(newValue);
                } else {
                    throw new Exception("Only staff may edit treatment notes");
                }
                break;
            default:
                System.out.println("Attribute " + attribute + " not supported!");
                break;
        }

        commitPatientFile();
    }

    private void commitPatientFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENT_FILE))) {
            for (PatientUsers patientUsers : patients) {
                writer.write(patientUsers.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void viewPatientInfo(String patientId) throws Exception {
        if (isPatient()) {
            throw new Exception("Patients cannot look up other patient info");
        }

        sortPatientsById();

        boolean found = false;
        int beginIndex = 0;
        int endIndex = patients.size();
        int midIndex;
        while ((beginIndex <= endIndex) && !found) {
            midIndex = (beginIndex + endIndex) / 2;
            if (patientId.compareTo(String.valueOf(patients.get(midIndex).getId())) == 0) {
                this.current = patients.get(midIndex);
                this.current.printInfo();
                found = true;
            } else if (patientId.compareTo(String.valueOf(patients.get(midIndex).getId())) < 0) {
                endIndex = midIndex - 1;
            } else if (patientId.compareTo(String.valueOf(patients.get(midIndex).getId())) > 0) {
                beginIndex = midIndex + 1;
            }
        }

        if (!found) {
            System.out.println("Patient not found");
        }
    }

    public PatientUsers getCurrent() {
        return current;
    }

    public void printReport(String fileName, String reportType) {
        switch (reportType) {
            case "A":
                reportPatientListById(fileName);
                break;
            case "B":
                reportPatientListByName(fileName);
                break;
            case "C":
                reportAllEmails(fileName);
                break;
            case "D":
                reportProfile(fileName);
                break;
            default:
                break;
        }
    }

    private void reportPatientListById(String fileName) {
        sortPatientsById();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Patient ID, name, email");
            writer.newLine();
            for (PatientUsers patient : patients) {
                writer.write(patient.getId() + ", " + patient.getName() + ", " + patient.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private void reportPatientListByName(String fileName) {
        sortPatientsByName();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Patient name, email, ID");
            writer.newLine();
            for (PatientUsers patient : patients) {
                writer.write(patient.getId() + ", " + patient.getName() + ", " + patient.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private void reportProfile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(loggedInUser.getInfoString());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private void reportAllEmails(String fileName) {
        sortPatientsByEmail();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Current user email");
            writer.newLine();
            writer.write(loggedInUser.getEmail());
            writer.newLine();
            writer.write("Patient emails");
            writer.newLine();
            for (PatientUsers patient : patients) {
                writer.write(patient.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private void sortPatientsByEmail() {
        int i, j;
        for (i = 1; i < patients.size(); i++) {
            PatientUsers tmp = patients.get(i);
            j = i;
            while ((j > 0) && (patients.get(j - 1).getEmail().compareTo(tmp.getEmail()) > 0)) {
                patients.set(j, patients.get(j - 1));
                j--;
            }
            patients.set(j, tmp);
        }
    }

    private void sortPatientsById() {
        int i, j;
        for (i = 1; i < patients.size(); i++) {
            PatientUsers tmp = patients.get(i);
            j = i;
            while ((j > 0) && (patients.get(j - 1).getId() > tmp.getId())) {
                patients.set(j, patients.get(j - 1));
                j--;
            }
            patients.set(j, tmp);
        }

    }

    private void sortPatientsByName() {
        int i, j;
        for (i = 1; i < patients.size(); i++) {
            PatientUsers tmp = patients.get(i);
            j = i;
            while ((j > 0) && (patients.get(j - 1).getName().compareTo(tmp.getName()) > 0)) {
                patients.set(j, patients.get(j - 1));
                j--;
            }
            patients.set(j, tmp);
        }
    }

}