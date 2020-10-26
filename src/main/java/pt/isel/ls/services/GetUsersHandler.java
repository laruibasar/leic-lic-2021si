package pt.isel.ls.services;

import pt.isel.ls.utils.CommandResult;

public class GetUsersHandler extends Handler implements IHandler {

    @Override
    public CommandResult execute() {
        System.out.println("Handler speaking: Getting all users");
        return null;
    }
}
