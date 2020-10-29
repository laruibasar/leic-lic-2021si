package pt.isel.ls.services;

import pt.isel.ls.utils.CommandResult;


/**
 * GET /users/{uid} - returns the details for the user identified by uid
 */
public class GetUserDetailsHandler extends Handler implements IHandler {
    @Override
    public CommandResult execute() {
        String query = "select * from users";
//        try (Statement stmt = con.createStatement()) {
//            ResultSet rs = stmt.executeQuery(query);
//        } catch (SQLException e) {
//            JDBCTutorialUtilities.printSQLException(e);
//        }
//        return rs;
        return null;
    }
}
