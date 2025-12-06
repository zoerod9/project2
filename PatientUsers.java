public class PatientUsers extends Users {

    private String treatment_notes;

    public PatientUsers(int id, String username) {
        super(id, username);
    }

    public String getTreatment_notes() {
        return treatment_notes;
    }

    public void setTreatment_notes(String treatment_notes) {
        this.treatment_notes = treatment_notes;
    }

    @Override
    public String toString() {
        return "";
    }

    // set info
    // can only set your own info
    // can't change id or username once they've been set
    // get info
    // can only view their own info

    // get info
    // can only view their own info
    public String printUserInfo() {
        return ("patent usrs");
    }

    // print user info using toString override ?

    // REPORTING
    // ask user to give filename to print report to and choose one of these:
    // all patients sorted by ascending ID
    // all patients sorted by ascending name
    // all emails, ascending alphabetically
    // user's info and patient/staff status

}
