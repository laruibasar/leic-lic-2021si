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

    public User(int id) {
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
            case 1:
                return "UserId = " + id + " Name = "+name + " Email = " + email;
            case 2:
                return "UserId = " + id;
            default:
                return null;
        }
    }
}
