package pt.isel.ls.services;

import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;

import java.sql.SQLException;

/**
 * POST /users - creates a new user, given the following parameters
 * name - the user's name
 * email - the user's unique email.
 */

public class CreateUserHandler extends Handler implements IHandler {

    public CreateUserHandler() {
        super();
        template.setParameters(new Parameters(new String[]{"name", "email"}));
    }

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
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
