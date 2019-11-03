package pl.coderslab.model;

import javafx.css.converter.LadderConverter;
import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.UserDao;
import pl.coderslab.dao.User_GroupDAO;

import java.util.Scanner;

public class User {
    private int id;
    private String userName;
    private String email;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        hashPassword(password);
    }

    public User() {
    }

    public void hashPassword(String password) {

        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
