package pt.isel.ls.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *  class responsible for saving the result of the query made to the database
 *  The ResultSet interface provides getter methods for retrieving column values
 *  from the current row.
 */

public class CommandResult<T> {

    public final T result;

    public CommandResult(T rs) {
        result = rs;
    }

    public String toString() {
        if (result instanceof ResultSet) {
            ResultSet rs = (ResultSet) result;
            StringBuilder sb = new StringBuilder();
            ResultSetMetaData rsmd = null;
            try {
                rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1) sb.append(",  ");
                        sb.append(rs.getString(i) + " " + rsmd.getColumnName(i));
                    }
                    sb.append("\n");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return sb.toString();
        } else {
            int Post = (int) result;
            return Post == 1 ? "Operation was a sucess" : "Operation failed miserable";
        }
    }
}
