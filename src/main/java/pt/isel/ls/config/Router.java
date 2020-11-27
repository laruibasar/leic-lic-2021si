package pt.isel.ls.config;

import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.Method;
import pt.isel.ls.utils.Path;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Router implements Iterable<Command> {

    private final Map<Command, Handler> handlers;

    public Router() {
        handlers = new LinkedHashMap<>();
    }

    public void addHandler(Method method, Path path, Handler handler) {
        handlers.put(new Command(method, path), handler);
    }

    public Handler findHandler(Command command) throws RouterException {
        if (command.getTemplate() == null) {
            throw new RouterException("Invalid command " + command.toString());
        }
        return handlers.get(command.getTemplate());
    }

    public Command findTemplate(Command command) throws RouterException {
        for (Command template : handlers.keySet()) {
            if (template.matches(command)) {
                return template;
            }
        }
        throw new RouterException("Invalid command " + command.toString());
    }

    public Handler getHandler(Command template) {
        return handlers.get(template);
    }

    @Override
    public Iterator<Command> iterator() {
        return handlers.keySet().iterator();
    }
}
