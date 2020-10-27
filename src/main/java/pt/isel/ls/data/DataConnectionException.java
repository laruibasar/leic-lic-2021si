package pt.isel.ls.data;

public class DataConnectionException extends Exception {
    public DataConnectionException(String description)
    {
        super(description);
    }

    public DataConnectionException(String description, Exception e) {
        super(description, e);
    }
}