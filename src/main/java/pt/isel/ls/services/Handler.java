package pt.isel.ls.services;

import pt.isel.ls.utils.Command;

public abstract class Handler implements IHandler {
    protected Command template;

    public void setTemplate(Command template) {
        this.template = template;
    }

    public Command getTemplate() {
        return template;
    }
}
