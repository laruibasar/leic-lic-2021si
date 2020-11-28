package pt.isel.ls.handlers.common;

import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

public interface IHandler {
    public CommandResult execute(Command cmd) throws HandlerException;
}
