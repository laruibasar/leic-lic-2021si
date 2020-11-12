package pt.isel.ls.data;

import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.User;
import pt.isel.ls.utils.CommandResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class UserData extends Data implements IUserData {

    public CommandResult createUser(String name, String email)
            throws DataConnectionException {
        Connection conn = null;
        LinkedList<Model> users = new LinkedList<>();
        CommandResult result = null;

        try {
            conn = getDataConnection().getConnection();
            final String query = "insert into users(name, email) values(?, ?)";

            PreparedStatement stmt = conn.prepareStatement(
                    query,
                    Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, name);
            stmt.setString(2, email);

            int status = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            User user = new User(rs.getInt(1), name, email);
            users.add(user);

            rs.close();
            stmt.close();
            result = new CommandResult(users, status);
            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new DataConnectionException("Failed to rollback\n"
                            + ex.getMessage());
                }
            }
            throw new DataConnectionException("Unable to add User\n"
                    + e.getMessage());
        } finally {
            closeConnection(conn);
        }

        return result;
    }

    public CommandResult getAllUsers() throws DataConnectionException {
        Connection conn = null;
        LinkedList<Model> users = new LinkedList<>();
        CommandResult result = null;

        try {
            conn = getDataConnection().getConnection();
            final String query = "select uid, name from users;";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(
                        new User(rs.getInt(1), rs.getString(2))
                );
            }

            rs.close();
            stmt.close();
            result = new CommandResult(users, users.size());
            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new DataConnectionException("Failed to rollback\n"
                            + ex.getMessage());
                }
            }
            throw new DataConnectionException("Unable to get a list of all the users\n"
                    + e.getMessage());
        } finally {
            closeConnection(conn);
        }

        return result;
    }

    public CommandResult getUser(int id) throws DataConnectionException {
        return null;
    }
}
