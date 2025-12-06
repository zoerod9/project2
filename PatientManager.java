import java.util.ArrayList;

public class PatientManager {

    private Users loggedInUser;
    private ArrayList<PatientUsers> patients;

    public PatientManager(Users user, ArrayList<PatientUsers> patients){
        this.loggedInUser = user;
        this.patients = patients;
    }

    public Users getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Users loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public ArrayList<PatientUsers> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<PatientUsers> patients) {
        this.patients = patients;
    }

    // instansiate upon successful login

    // hold user that is currently logged on as general user class
    // display information

    // load an arrayList of all patients
    // instantiate a new Patient and add it to the ArrayList for EVERY
    // Patient in the file)
    // sort pts by id

    // view/lookup pts
    // throw error for patient users
    // binary search
    // if found, set as 'currently viewed'

    // edit current pt
    // update in arrayList
    // changes should be reflected in patient file
    // overwrite entire patient file, print entire arrayList
}