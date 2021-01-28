package pt.isel.ls.data.common;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DataConnection defines the methods for connection to use to a database.
 */
public class DataConnection {
    private String url;

    public DataConnection(String url) {
        this.url = url;
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;

        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setURL(url);
        conn = ds.getConnection();

        return conn;
    }
}
