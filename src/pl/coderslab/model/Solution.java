package pl.coderslab.model;


import pl.coderslab.dao.ExerciseDAO;
import pl.coderslab.dao.SolutionDAO;
import pl.coderslab.dao.UserDao;

import java.sql.Timestamp;
import java.util.Scanner;

public class Solution {

    private int id;
    private Timestamp created;
    private Timestamp updated;
    private String description;
    private int users_id;
    private int exercise_id;

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", description='" + description + '\'' +
                ", users_id=" + users_id +
                ", exercise_id=" + exercise_id +
                '}';
    }

    public Solution(int id, Timestamp created, Timestamp updated, String description) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.description = description;
    }

    public Solution() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }


}
