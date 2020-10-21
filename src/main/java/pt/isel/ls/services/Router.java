package pt.isel.ls.services;

import java.util.ArrayList;
import java.util.List;

public class Router {

    /**
     * Queue for the different handlers
     */
    private List<CommandHandler> requests = new ArrayList<>();

    public void addRequest(Method method, PathTemplate template, CommandHandler handler) {
        requests.add(handler);
    }

    public void requestDispatcher() {
        for(CommandHandler r: requests)
            r.execute(r);
    }


//    public Optional<RouteResult> findRoute(Method method, Path path) {...}
}
