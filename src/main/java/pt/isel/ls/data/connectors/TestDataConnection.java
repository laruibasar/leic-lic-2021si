package pt.isel.ls.data.connectors;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.config.AppConfig;
import pt.isel.ls.config.DataBaseConfig;
import pt.isel.ls.data.common.DataConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDataConnection extends DataConnection {
    private static TestDataConnection dataConnection = null;

    public static DataConnection getInstance() {
        if (dataConnection == null) {
            dataConnection = new TestDataConnection();
        }
        return dataConnection;
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;

        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setURL(getConnectionUrl());
        conn = ds.getConnection(getConnectionUser(), getConnectionPassword());

        /*
         * Set autocommit to false, important to consider always transaction
         * in nature. We close with a commit() ou rollback().
         * Note: multiple selects should be in a transaction, so commit!
         */
        conn.setAutoCommit(false);

        return conn;
    }

    private String getConnectionUrl() {
        DataBaseConfig dbc = AppConfig.getDatabaseInfo();
        String url = "jdbc:postgresql://"
                + dbc.host
                + ":" + dbc.port
                + "/" + dbc.database;

        return url;
    }
}
