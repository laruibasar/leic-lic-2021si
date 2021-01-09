package pt.isel.ls.config;

import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.connectors.PsqlDataConnection;
import pt.isel.ls.data.connectors.TestDataConnection;

public class DataBaseConfig {
    public static final String DatabaseType_Test = "Test";
    public static final String DatabaseType_PSQL = "Postgresql";

    public String host;
    public int port;
    public String database;
    public String user;
    public String password;
    public String databaseType;

    public DataBaseConfig() throws EnvironmentVariableException {
        host = System.getenv("LS_DB_HOST");
        if (host == null || host.length() == 0) {
            throw new EnvironmentVariableException("LS_DB_HOST not set");
        }

        String getPort = System.getenv("LS_DB_PORT");
        if (getPort == null || Integer.parseInt(getPort) == 0) {
            port = 5432; /* standard postgresql port */
        } else {
            port = Integer.parseInt(getPort);
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
        databaseType = System.getenv("LS_DB_TYPE");
        if (databaseType ==  null || password.length() == 0) {
            // throw new EnvironmentVariableException("LS_DB_TYPE not set, "
            //        + "choose Test or Postgresql");
            Data.setDataConnection(PsqlDataConnection.getInstance());
        }

        if (DatabaseType_PSQL.equals(databaseType)) {
            Data.setDataConnection(PsqlDataConnection.getInstance());
        }

        /* Test Database, so we can test and write our data */
        if (DatabaseType_Test.equals(databaseType)) {
            Data.setDataConnection(TestDataConnection.getInstance());
        }
    }
}
