package pt.isel.ls.services;

public class HandlerException extends Exception {
    public HandlerException(String description) {
        super(description);
    }

    public HandlerException(String description, Exception e) {
        super(description, e);
    }
}
