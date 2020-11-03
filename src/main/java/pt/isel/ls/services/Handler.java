package pt.isel.ls.services;

import pt.isel.ls.utils.Command;

public abstract class Handler implements IHandler {
    protected Command template;

    public Handler() {
        template = new Command();
    }

    public Command getTemplate() {
        return template;
    }
}
