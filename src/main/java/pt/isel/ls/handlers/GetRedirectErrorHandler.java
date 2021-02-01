package pt.isel.ls.handlers;

import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.results.GetRedirectErrorResult;
import pt.isel.ls.utils.Command;

public class GetRedirectErrorHandler extends Handler implements IHandler {
    public GetRedirectErrorHandler() {
        super();
        description = "Display redirect error";

        validValues.add("error");
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        return new GetRedirectErrorResult();
    }
}
