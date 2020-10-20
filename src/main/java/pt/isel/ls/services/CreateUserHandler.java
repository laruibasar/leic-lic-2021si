package pt.isel.ls.services;

import pt.isel.ls.model.User;

public class CreateUserHandler extends Handler {
    private User user;

    public CreateUserHandler(String name, String email) {
        user = new User(name, email);
    }

    @Override
    public int execute() {
        return 0;
    }
}
