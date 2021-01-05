package pt.isel.ls;

import pt.isel.ls.config.AppConfig;
import pt.isel.ls.config.RouterException;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.Header;
import pt.isel.ls.utils.Method;
import pt.isel.ls.utils.Parameters;
import pt.isel.ls.utils.Path;

public class AppCommand {

    public static Command setCommand(String[] args) throws RouterException {
        Method method = Method.getMethod(args[0].toUpperCase());
        if (method == null) {
            throw new RouterException("Command method invalid " + args[0]);
        }

        Path path = new Path(args[1]);
        Header header = new Header();
        Parameters params = new Parameters();

        if (args.length == 3) {
            if (args[2].contains("accept:") || args[2].contains("file-name:")) {
                header.setValues(args[2]);
            } else {
                params.setValues(args[2]);
            }
        } else if (args.length == 4) {
            header.setValues(args[2]);
            params.setValues(args[3]);
        }
        Command cmd = new Command(method, path, header, params);

        try {
            cmd.setTemplate(AppConfig.getRouter().findTemplate(cmd));
        } catch (RouterException e) {
            throw new RouterException(e.getMessage());
        }

        return cmd;
    }

    public static CommandResult runCommand(Command cmd) throws RouterException,
            HandlerException {
        Handler handler = AppConfig.getRouter().findHandler(cmd);
        return handler.execute(cmd);
    }
}
