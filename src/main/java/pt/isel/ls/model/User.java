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

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("UserId = " + id + " Name = " + name);
        if (email != null) {
            str.append(" Email = " + email);
        }
        return str.toString();
    }
}
