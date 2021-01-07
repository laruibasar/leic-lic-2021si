package pt.isel.ls.handlers;

import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.results.RootResult;
import pt.isel.ls.utils.Command;

public class RootHandler extends Handler implements IHandler {
    public RootHandler() {
        super();
        description = "Show root page";
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        return new RootResult();
    }
}
