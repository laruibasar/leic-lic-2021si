package pt.isel.ls.model;

public class User extends Model {
    private int id;
    private String name;
    private String email;

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


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("UserId = " + id + " Name = " + name);
        if (email != null) {
            str.append(" Email = " + email);
        }
        return str.toString();
    }
}
