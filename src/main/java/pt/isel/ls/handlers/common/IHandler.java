package pt.isel.ls.handlers.common;

import pt.isel.ls.utils.Command;
import pt.isel.ls.view.results.CommandResult;

public interface IHandler {
    public CommandResult execute(Command cmd) throws HandlerException;
}
