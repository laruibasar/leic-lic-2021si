package pt.isel.ls.services;

import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.SQLException;

/**
 * GET /movies - returns a list with all movies.
 */

public class GetMoviesHandler extends Handler implements IHandler {

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        String query = "select name from movies;";
//        try (Statement stmt = con.createStatement()) {
//            ResultSet rs = stmt.executeQuery(query);
//        } catch (SQLException e) {
//            JDBCTutorialUtilities.printSQLException(e);
//        }
        return null;
    }
}
