package pt.isel.ls.handlers;

import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.results.CommandResult;
import pt.isel.ls.view.results.ExitResult;

public class ExitHandler extends Handler implements IHandler {
    public ExitHandler() {
        super();
        description = "Exit program";
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        return new ExitResult();
    }
}
