package pt.isel.ls.handlers;

import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.results.ExitResult;

public class ExitHandler extends Handler implements IHandler {
    public ExitHandler() {
        super();
        description = "Exit program";
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        try {
            return new ExitResult();
        } catch (Exception e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
