
package edu.dottn.entities;

//import java.io.Serializable;

public class Association extends user  {
    private int id;
    private String assocName;
    private String role;
    private String username;
    private String password;
    private String email;
    private String location;
    private int number;

    public Association() {
        
    }

    public Association(String assocName, String username, String email, String location, int number) {
        this.assocName = assocName;
        this.username = username;
        this.email = email;
        this.location = location;
        this.number = number;
    }

    
    public Association(String role ,String assocName, String username, String password, String email, String location, int number) {
        this.role = role;
        this.assocName = assocName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.location = location;
        this.number = number;
    }

    public Association(int id, String assocName, String username, String email, String location, int number) {
        this.id = id;
        this.assocName = assocName;
        this.username = username;
        this.email = email;
        this.location = location;
        this.number = number;
    }
    

    public Association(int id, String assocName, String username, String password, String email, String location, int number) {
        this.id = id;
        this.assocName = assocName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.location = location;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getRole(){
        return role;
    }


    public String getAssocName() {
        return assocName;
    }

    public void setAssocName(String assocName) {
        this.assocName = assocName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Association{" + "assocName=" + assocName + ", username=" + username + ", email=" + email + ", location=" + location + ", number=" + number + '}';
    }
    
}