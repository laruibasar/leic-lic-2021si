package pt.isel.ls.services;

import pt.isel.ls.model.User;

public class CreateUserHandler implements CommandHandler<CreateUserHandler>{

    private String name;
    private String mail;

    public CreateUserHandler(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }




    @Override
    public int execute(CreateUserHandler command) {
        return 0;
    }
}
