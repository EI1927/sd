package BeanClasses;


public class User {
    int id;
    private String firstname, lastname, emailid, phone, password, status, role;

    public User() {
    }

    public User(int id, String firstname, String lastname, String emailid, String phone, String password, String status, String role) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailid = emailid;
        this.phone = phone;
        this.password = password;
        this.status = status;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getRole(){
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}
