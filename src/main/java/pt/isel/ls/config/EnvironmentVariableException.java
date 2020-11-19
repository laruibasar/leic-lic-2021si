package pt.isel.ls.config;

public class EnvironmentVariableException extends Exception {
    public EnvironmentVariableException(String description) {
        super("Environment variable: " + description);
    }
}