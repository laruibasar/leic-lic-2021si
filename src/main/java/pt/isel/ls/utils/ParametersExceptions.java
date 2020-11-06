package pt.isel.ls.utils;

public class ParametersExceptions extends Exception {
    public ParametersExceptions(String description) {
        super("Parameters: " + description);
    }
}
