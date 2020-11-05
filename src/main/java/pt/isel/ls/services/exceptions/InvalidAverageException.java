package pt.isel.ls.services.exceptions;

public class InvalidAverageException extends Throwable {
    public InvalidAverageException(String message) {
        super("Average parameter must be: highest or lowest. Sent: " + message);
    }
}