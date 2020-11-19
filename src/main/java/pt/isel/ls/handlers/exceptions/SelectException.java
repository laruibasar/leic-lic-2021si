package pt.isel.ls.handlers.exceptions;

public class SelectException extends Exception {

    public SelectException(String message) {
        super("Select: " + message);
    }
}
