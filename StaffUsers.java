public class StaffUsers extends Users {

    private String department;

    public StaffUsers(int id, String username) {
        super(id, username);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("- Staff info:");
        System.out.println("  department: " + department);
    }

    @Override
    public String getInfoString() {
        return super.getInfoString() +
                "- Staff info:" + "\n" +
                "  department: " + department + "\n";
    }

}
