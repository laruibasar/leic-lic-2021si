package pt.isel.ls.services;

import pt.isel.ls.model.User;

public class CreateUserHandler implements CommandHandler<CreateUserHandler>{

    private User user;

    public CreateUserHandler(String name, String email) {
        user = new User(name, email);
    }


    @Override
    public void execute(CreateUserHandler command) {

    }
}
