package pl.coderslab.model;

import pl.coderslab.dao.User_GroupDAO;

import java.util.Scanner;

public class User_group {

    private int id;
    private String name;

    @Override
    public String toString() {
        return "User_group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public User_group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User_group() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
