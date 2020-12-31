package pt.isel.ls.config;

import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.utils.Command;

public class Router {

    private final Tree tree;

    public Router(Tree tree) {
        this.tree = tree;
    }

    public Handler findHandler(Command command) throws RouterException {
        Handler handler = tree.lookForHandler(tree.getRoot(), command);
        if (command.getTemplate() == null) {
            throw new RouterException("Invalid command " + command.toString());
        }
        return handler;
    }

    public Command findTemplate(Command command) throws RouterException {
        Command cmd = tree.findCommand(tree.getRoot(), command);
        if (cmd.getTemplate() == null) {
            throw new RouterException("Invalid command " + command.toString());
        }
        return cmd.getTemplate();
    }

    public Handler getHandler(Command template) throws RouterException {
        return findHandler(template);
    }

    public Tree getTree() {
        return tree;
    }

}
