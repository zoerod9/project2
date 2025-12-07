public abstract class Users {

    private int id;
    private String username;
    private String password;
    private String name;
    private String email;

    public Users(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public void setPassword(String passwordName) {
        password = passwordName;
    }

    public void setName(String namePatient) {
        name = namePatient;
    }

    public void setEmail(String emailName) {
        email = emailName;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return id + "," + username + "," + password + "," + name + "," + email;
    }

    protected void printInfo() {
        System.out.println("- User info:");
        System.out.println("  id: " + id);
        System.out.println("  username: " + username);
        System.out.println("  password: " + password);
        System.out.println("  name: " + name);
        System.out.println("  email: " + email);
    };

    public String getInfoString() {
        return "- User info:" + "\n" +
                "  id: " + id + "\n" +
                "  username: " + username + "\n" +
                "  password: " + password + "\n" +
                "  name: " + name + "\n" +
                "  email: " + email + "\n";
    }
}
