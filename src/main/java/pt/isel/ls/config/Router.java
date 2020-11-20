package pt.isel.ls.config;

import pt.isel.ls.handlers.Handler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.Method;
import pt.isel.ls.utils.Path;

import java.util.LinkedHashMap;
import java.util.Map;

public class Router {

    private final Map<Command, Handler> handlers;

    public Router() {
        handlers = new LinkedHashMap<>();
    }

    public void addHandler(Method method, Path path, Handler handler) {
        handlers.put(new Command(method, path), handler);
    }

    public Handler findHandler(Command command) throws RouterException {
        for (Command template : handlers.keySet()) {
            if (template.matches(command)) {
                command.setValues(template.getPath()); // awfull code
                return handlers.get(template);
            }
        }
        throw new RouterException("Invalid command " + command.toString());
    }
}
