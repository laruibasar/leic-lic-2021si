package pt.isel.ls.handlers;

import pt.isel.ls.config.AppConfig;
import pt.isel.ls.config.Router;
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
    public CommandResult execute(Command cmd) throws HandlerException {
        Router router = AppConfig.getRouter();

        for (Command c : router) {
            System.out.println(router.getHandler(c).getDescription() + "\n"
                    + "Command: " + c.toString() + "\n");
        }
        return null;
    }
}
