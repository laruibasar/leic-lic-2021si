package pt.isel.ls.config;

import pt.isel.ls.services.Handler;
import pt.isel.ls.utils.Command;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class Router {

    /* store the application available handlers */
    //private static Hashtable<String, Handler> getHandlers;
    //private static Hashtable<String, Handler> postHandlers;

    private static List<Handler> handlers;

    public Router() throws RouterException {
        //getHandlers = new Hashtable<>();
        //postHandlers = new Hashtable<>();
        handlers = new ArrayList<>();
    }

    /*
    public static void addHandler(String method, String path,
                                  Handler handler) throws RouterException {
        switch (method) {
            case "post":
                postHandlers.put(path, handler);
                break;
            case "get":
                getHandlers.put(path, handler);
                break;
            default:
                throw new RouterException("Router: invalid method " + method);
        }
    }
    */

    public static void addHandler(String method, String path, Handler handler) {
        Command template = new Command(method, path);
        handler.setTemplate(template);
        handlers.add(handler);
    }

    public static Handler getHandler(Command command) throws RouterException {
        Iterator<Handler> it = handlers.iterator();
        while (it.hasNext()) {
            Handler handler = it.next();
            if (handler.getTemplate().equals(command)) {
                return handler;
            }
        }
        throw new RouterException("Router: invalid command");
    }
}
