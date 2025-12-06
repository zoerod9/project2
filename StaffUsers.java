public class StaffUsers extends Users {

    private String department;

    public StaffUsers(int id, String username){
        super(id, username);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString(){
        return "";
    }
}
