package pt.isel.ls.config;

import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.Method;
import pt.isel.ls.utils.Path;

import java.util.ArrayList;
//import java.util.Hashtable;
import java.util.List;

public class Router {

    private final List<Handler> handlers;

    public Router() {
        handlers = new ArrayList<>();
    }

    public void addHandler(Method method, Path path, Handler handler) {
        handler.getTemplate().setMethod(method);
        handler.getTemplate().setPath(path);
        handlers.add(handler);
    }

    public  Handler findHandler(Command command) throws RouterException {
        for (Handler handler : handlers) {
            if (handler.getTemplate().matches(command)) {
                return handler;
            }
        }
        throw new RouterException("Invalid command " + command.toString());
    }
}
