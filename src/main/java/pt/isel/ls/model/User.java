package pt.isel.ls.model;

public class User extends Model {
    private int id;
    private String name;
    private String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
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
                return "UserId = " + id + " Name = " + name + " Email = " + email;
            case 1:
                return "UserId = " + id;
            default:
                return null;
        }
    }
}
