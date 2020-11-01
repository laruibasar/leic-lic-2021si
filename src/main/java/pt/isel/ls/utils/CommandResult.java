package pt.isel.ls.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *  class responsible for saving the result of the query made to the database
 *  The ResultSet interface provides getter methods for retrieving column values
 *  from the current row.
 */

public class CommandResult {

    public final ResultSet result;

    public CommandResult(ResultSet rs) {
        result = rs;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        ResultSetMetaData rsmd = null;
        try {
            rsmd = result.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (result.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) sb.append(",  ");
                    String columnValue = result.getString(i);
                    sb.append(columnValue + " " + rsmd.getColumnName(i));
                }
                sb.append("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb.toString();
    }
}
