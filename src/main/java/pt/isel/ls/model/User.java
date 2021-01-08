package pt.isel.ls.model;

import java.util.ArrayList;

public class User extends Model {
    private int id;
    private String name;
    private String email;
    private ArrayList<Review> reviews = new ArrayList<>();

    public User(){

    }

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("UserId = " + id + " Name = " + name);
        if (email != null) {
            str.append(" Email = " + email);
        }
        return str.toString();
    }
}
