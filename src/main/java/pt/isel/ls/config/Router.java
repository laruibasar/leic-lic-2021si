package pt.isel.ls.config;

import pt.isel.ls.services.Handler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.Method;

import java.util.ArrayList;
//import java.util.Hashtable;
import java.util.List;

public class Router {

    private final List<Handler> handlers;

    public Router() {
        handlers = new ArrayList<>();
    }

    public void addHandler(Method method, String path, Handler handler) {
        Command template = new Command(method, path);
        handler.setTemplate(template);
        handlers.add(handler);
    }

    public  Handler findHandler(Command command) throws RouterException {
        for (Handler handler : handlers) {
            if (handler.getTemplate().equals(command)) {
                return handler;
            }
        }
        throw new RouterException("Router: invalid command");
    }
}
