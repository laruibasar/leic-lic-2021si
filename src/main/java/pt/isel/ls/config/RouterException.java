package pt.isel.ls.config;

public class RouterException extends Exception {
    public RouterException(String description) {
        super("Router: " + description);
    }
}
