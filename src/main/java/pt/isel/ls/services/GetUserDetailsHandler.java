package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.User;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.sql.SQLException;


/**
 * GET /users/{uid} - returns the details for the user identified by uid
 */

public class GetUserDetailsHandler extends Handler implements IHandler {

    private LinkedList<Model> users = new LinkedList<>();

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        Connection conn = null;
        try {
            conn = Data.getDataConnection().getConnection();
            final String query = "select uid, name, email from users where uid = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(cmd.getPath().getPath().get(1)));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
            conn.commit();
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }

            throw new DataConnectionException("Unable to get a list of all the movies\n"
                    + e.getMessage(), e);
        } finally {
            Data.closeConnection(conn);
        }

        return new CommandResult(users,users.size());
    }
}
