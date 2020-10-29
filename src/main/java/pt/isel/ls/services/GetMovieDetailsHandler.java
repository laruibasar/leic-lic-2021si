package pt.isel.ls.services;

import pt.isel.ls.utils.CommandResult;


/**
 * GET /movies/{mid} - returns the detailed information for the movie identified by mid
 *  rating - integer between 1 and 5.
 */

public class GetMovieDetailsHandler extends Handler implements IHandler {
    @Override
    public CommandResult execute() {
        String query = "select * from movies;";
//        try (Statement stmt = con.createStatement()) {
//            ResultSet rs = stmt.executeQuery(query);
//        } catch (SQLException e) {
//            JDBCTutorialUtilities.printSQLException(e);
//        }
        return null;
    }
}
