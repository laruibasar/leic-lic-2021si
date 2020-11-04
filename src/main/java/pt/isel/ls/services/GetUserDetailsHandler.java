package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.User;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.*;
import java.util.LinkedList;


/**
 * GET /users/{uid} - returns the details for the user identified by uid
 */

public class GetUserDetailsHandler extends Handler implements IHandler {

    private LinkedList<User> user = new LinkedList<>();
    private LinkedList<String> tuple = new LinkedList<>();
    private final String query = "select * from users where uid = ?;";

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        Data mapper = new Data();
        Connection conn = null;
        try {
            conn = mapper.getDataConnection().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, cmd.getPath().getPath().get(1));
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    tuple.add(rs.getString(i));
                }
                user.add(new User(
                        Integer.parseInt(tuple.get(0)),
                        tuple.get(1),
                        tuple.get(2)));
                tuple.clear();
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
            mapper.closeConnection(conn);
        }

        return new CommandResult(user);
    }
}
