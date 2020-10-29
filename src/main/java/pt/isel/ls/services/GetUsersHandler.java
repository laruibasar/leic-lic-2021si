package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.Connection;
import java.sql.SQLException;

public class GetUsersHandler extends Handler implements IHandler {

    /**
     * GET /users - returns the list of users.
     */

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        System.out.println("Handler speaking: Getting all users");

        Data mapper = new Data();
        Connection conn = null;
        try {
            conn = mapper.getDataConnection().getConnection();

            /* insert queries, statements and results */

            conn.commit();

            return null;
        } catch (Exception e) {
            conn.rollback();
            throw new DataConnectionException("Unable to list all users\n"
                    + e.getMessage(), e);
        } finally {
            mapper.closeConnection(conn);
        }
    }
}
