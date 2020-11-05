package pt.isel.ls.services.exceptions;

public class SelectException extends Exception {

    public SelectException(String message) {
        super("Select: " + message);
    }
}
