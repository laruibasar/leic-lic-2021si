package pt.isel.ls.handlers;

import pt.isel.ls.config.AppConfig;
import pt.isel.ls.config.Router;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.results.OptionResult;

public class OptionHandler extends Handler implements IHandler {
    public OptionHandler() {
        super();
        description = "Show a list of available commands and their description";
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        Router router = AppConfig.getRouter();

        return new OptionResult(router);
    }
}
