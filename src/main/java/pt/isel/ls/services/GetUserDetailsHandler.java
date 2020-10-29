package pt.isel.ls.services;

import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.SQLException;


/**
 * GET /users/{uid} - returns the details for the user identified by uid
 */
public class GetUserDetailsHandler extends Handler implements IHandler {

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
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
