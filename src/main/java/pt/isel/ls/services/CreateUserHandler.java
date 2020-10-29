package pt.isel.ls.services;

import pt.isel.ls.utils.CommandResult;

/**
 * POST /users - creates a new user, given the following parameters
 * name - the user's name
 * email - the user's unique email.
 */

public class CreateUserHandler extends Handler implements IHandler {

    @Override
    public CommandResult execute() {
        String query = "insert into users(uid, fname, lname, email) values\n" +
                //incrementar último uid
                "(uid," +
                "'"+ "'," +
                "'"+ "'," +
                "'"+ "',";
//        try (Statement stmt = con.createStatement()) {
//            ResultSet rs = stmt.executeQuery(query);
//        } catch (SQLException e) {
//            JDBCTutorialUtilities.printSQLException(e);
//        }
//        return rs;
        return null;
    }
}
