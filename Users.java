public class Users {
    
    private int id;
    private String username;
    public String password;
    public String name;
    public String email;

    // print user info
    public String printUserInfo(){
        System.out.println(id);
        System.out.println(username);
        System.out.println(password);
        System.out.println(name);
        System.out.println(email);
        return"printuserinfo";
    }

    public void setUsername(String usernameName){
        username = usernameName;
    }
    
    public void setPassword(String passwordName){
        password = passwordName;
    }
    
    public void setName(String namePatient){
        name = namePatient;
    }
    
    public void setEmail(String emailName){
        email = emailName;
    }

    public int getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
    public String getName(){
        return name;
    }
    

    public String getEmail(){
        return email;
    }
}
