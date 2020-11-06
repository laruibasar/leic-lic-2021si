package pt.isel.ls.data;

import java.sql.Connection;
import java.sql.SQLException;

public class Data {
    private static DataConnection dataConnection = null;

    public static DataConnection getDataConnection() {
        return dataConnection;
    }

    public static void setDataConnection(DataConnection data) {
        dataConnection = data;
    }

    public static void closeConnection(Connection conn) throws DataConnectionException {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DataConnectionException(e.getMessage());
            }
        }
    }
}
