package pt.isel.ls.utils;

import java.sql.ResultSet;

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
}
