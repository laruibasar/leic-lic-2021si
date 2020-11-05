package pt.isel.ls.config;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnection;

public class DataBaseConfig {
    public String host;
    public int port;
    public String database;
    public String user;
    public String password;

    public DataBaseConfig() throws EnvironmentVariableException {
        host = System.getenv("LS_DB_HOST");
        if (host == null || host.length() == 0) {
            throw new EnvironmentVariableException("LS_DB_HOST not set");
        }

        String getPort = System.getenv("LS_DB_PORT");
        if (getPort == null || Integer.getInteger(getPort) == 0) {
            port = 5432; /* standard postgresql port */
        } else {
            port = Integer.getInteger(getPort);
        }

        database = System.getenv("LS_DB_DATABASE");
        if (database == null || database.length() == 0) {
            throw new EnvironmentVariableException("LS_DB_DATABASE not set");
        }

        user = System.getenv("LS_DB_USER");
        if (user == null || user.length() == 0) {
            throw new EnvironmentVariableException("LS_DB_USER not set");
        }

        password = System.getenv("LS_DB_PASSWD");
        if (password == null || password.length() == 0) {
            throw new EnvironmentVariableException("LS_DB_PASSWD not set");
        }

        /* Setup a data connection to use */
        Data.setDataConnection(DataConnection.getInstance());
    }
}
