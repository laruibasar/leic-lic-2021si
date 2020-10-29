package pt.isel.ls.data;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.config.AppConfig;
import pt.isel.ls.config.DataBaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DataConnection sets the connection to use to a database.
 * This case we set a Postgresql but we can change this class to allow
 * a setup of a class Connection Factory for different db drivers
 */
public class DataConnection {
    private static DataConnection dataConnection = null;

    public static DataConnection getInstance() {
        if (dataConnection == null) {
            dataConnection = new DataConnection();
        }
        return dataConnection;
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;

        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setURL(getConnectionUrl());
        conn = ds.getConnection(getConnectionUser(), getConnectionPassword());

        /* Set autocommit to false, important to consider always transaction
         * in nature. We close with a commit() ou rollback().
         * Note: multiple selects should be in a transaction, so commit!
         */
        conn.setAutoCommit(false);

        return conn;
    }

    private String getConnectionUrl() {
        DataBaseConfig dbc = AppConfig.getInstance().database;
        String url = "jdbc:postgresql://"
                + dbc.host
                + ":" + dbc.port
                + "/" + dbc.database;

        return url;
    }

    private String getConnectionUser() {
        DataBaseConfig dbc = AppConfig.getInstance().database;
        return dbc.user;
    }

    private String getConnectionPassword() {
        DataBaseConfig dbc = AppConfig.getInstance().database;
        return dbc.password;
    }
}
