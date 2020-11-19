package pt.isel.ls.handlers.exceptions;

public class InvalidAverageException extends Throwable {
    public InvalidAverageException(String message) {
        super("Average parameter must be: highest or lowest. Sent: " + message);
    }
}