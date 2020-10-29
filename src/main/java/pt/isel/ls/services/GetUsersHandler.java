package pt.isel.ls.services;

import pt.isel.ls.utils.CommandResult;

public class GetUsersHandler extends Handler implements IHandler {

    /**
     * GET /users - returns the list of users.
     */

    @Override
    public CommandResult execute() {
        String query = "select fname, lname from users";
//        try (Statement stmt = con.createStatement()) {
//            ResultSet rs = stmt.executeQuery(query);
//        } catch (SQLException e) {
//            JDBCTutorialUtilities.printSQLException(e);
//        }
//        return rs;
        return null;
    }
}
