package pt.isel.ls.handlers.exceptions;

public class InsertException extends Exception {

    public InsertException(String message) {
        super("Insert: " + message);
    }
}
