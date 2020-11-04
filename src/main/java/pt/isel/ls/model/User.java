package pt.isel.ls.model;

public class User extends Model {
    private int id;
    private String name;
    private String email;

    public User(int id, String name, String email, int selector) {
        super(selector);
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(int id, int selector) {
        super(selector);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        switch (selector) {
            case 3:
                //GET USER DETAILS
                return "UserId = " + id + " Name = " + name + " Email = " + email;
            case 1:
                //CREATE USER
                return "UserId = " + id + " Name = " + name;
            case 2:
                //GET ALL USERS
                return "Name = " + name;
            default:
                return null;
        }
    }
}
