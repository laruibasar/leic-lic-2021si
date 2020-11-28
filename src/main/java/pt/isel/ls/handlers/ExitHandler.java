package pt.isel.ls.handlers;

import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

public class ExitHandler extends Handler implements IHandler {
    public ExitHandler() {
        super();
        description = "Exit program";
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        return new CommandResult(null, 0);
    }
}
