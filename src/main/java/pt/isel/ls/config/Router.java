package pt.isel.ls.config;

import pt.isel.ls.services.Handler;

import java.util.ArrayList;
import java.util.List;

public class Router {

    /* store the application available handlers */
    private static List<Handler> handlers;

    public Router() throws RouterException {
        handlers = new ArrayList<>();
    }

    /* TODO: this method is to add a handler to our list of routing allowed.
     *       We care more with store the class of Handler
     *       than the setup of handler
     */
    public static void addHandler(String method, String template,
                                  String parameters) {
        // TODO: code to create a handler and store in list
    }

    public static Handler getHandler(String request) {
        // TODO: code to search and return new instance of and Handler
        return null;
    }
}
