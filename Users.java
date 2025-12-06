public abstract class Users {

    private int id;
    private String username;
    private String password;
    private String name;
    private String email;

    // // print user info
    // public String printUserInfo(){
    // System.out.println(id);
    // System.out.println(username);
    // System.out.println(password);
    // System.out.println(name);
    // System.out.println(email);
    // return"printuserinfo";
    // }

    public Users(int id, String username){
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

    public String toString(){
        return "";
    }
}
