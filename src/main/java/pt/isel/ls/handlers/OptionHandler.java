package pt.isel.ls.handlers;

import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

public class OptionHandler extends Handler implements IHandler {
    public OptionHandler() {
        super();
        description = "Show a list of available commands and their description";
    }

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, HandlerException {
        return null;
    }
}
