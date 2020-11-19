package pt.isel.ls.handlers;

public class HandlerException extends Exception {
    public HandlerException(String description) {
        super(description);
    }

    public HandlerException(String description, Exception e) {
        super(description, e);
    }
}
