package pt.isel.ls.data.common;

import pt.isel.ls.config.AppConfig;
import pt.isel.ls.config.DataBaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DataConnection defines the methods for connection to use to a database.
 */
public abstract class DataConnection {
    public abstract Connection getConnection() throws SQLException;

    protected String getConnectionUser() {
        DataBaseConfig dbc = AppConfig.getInstance().database;
        return dbc.user;
    }

    protected String getConnectionPassword() {
        DataBaseConfig dbc = AppConfig.getInstance().database;
        return dbc.password;
    }
}
