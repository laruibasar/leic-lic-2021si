package pt.isel.ls.services;

import pt.isel.ls.utils.CommandResult;

/**
 * GET /movies - returns a list with all movies.
 */

public class GetMoviesHandler extends Handler implements IHandler {

    @Override
    public CommandResult execute() {
        String query = "select name from movies;";
//        try (Statement stmt = con.createStatement()) {
//            ResultSet rs = stmt.executeQuery(query);
//        } catch (SQLException e) {
//            JDBCTutorialUtilities.printSQLException(e);
//        }
        return null;
    }
}
