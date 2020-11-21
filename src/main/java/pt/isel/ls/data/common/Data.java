
package pt.isel.ls.data.common;

import pt.isel.ls.data.connectors.DataConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Data {
    private static DataConnection dataConnection = null;

    public DataConnection getDataConnection() {
        return dataConnection;
    }

    public static void setDataConnection(DataConnection data) {
        dataConnection = data;
    }

    public void rollbackConnection(Connection conn) throws DataConnectionException {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new DataConnectionException("Failed to rollback\n"
                        + e.getMessage());
            }
        }
    }

    public void closeConnection(Connection conn) throws DataConnectionException {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DataConnectionException(e.getMessage());
            }
        }
    }
}
