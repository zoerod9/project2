public class PatientUsers extends Users{

    private String treatmentNotes;
    
    // set info
        // can only set your own info
        // can't change id or username once they've been set
    // get info
        // can only view their own info
   
    // get info
        // can only view their own info
    public String printUserInfo(){
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
