package pt.isel.ls.mockdata;

import pt.isel.ls.data.IUserData;
import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.User;
import pt.isel.ls.utils.CommandResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

public class MockUserData extends Data implements IUserData {
    @Override
    public CommandResult createUser(String name, String email) throws DataConnectionException {
        Connection conn = null;
        LinkedList<Model> users = new LinkedList<>();
        CommandResult result;

        try {
            conn = getDataConnection().getConnection();
            final String query = "insert into users(name, email) values(?, ?)";

            PreparedStatement stmt = conn.prepareStatement(
                    query,
                    Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, name);
            stmt.setString(2, email);

            final int status = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            User user = new User(rs.getInt(1), name, email);
            users.add(user);

            rs.close();
            stmt.close();
            result = new CommandResult(users, status);
            rollbackConnection(conn);
        } catch (Exception e) {
            rollbackConnection(conn);
            throw new DataConnectionException("Unable to add User\n"
                    + e.getMessage());
        } finally {
            closeConnection(conn);
        }

        return result;
    }

    @Override
    public CommandResult getAllUsers() throws DataConnectionException {
        Connection conn = null;
        LinkedList<Model> users = new LinkedList<>();
        CommandResult result;

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
            rollbackConnection(conn);
        } catch (Exception e) {
            rollbackConnection(conn);
            throw new DataConnectionException("Unable to get a list of all the users\n"
                    + e.getMessage());
        } finally {
            closeConnection(conn);
        }

        return result;
    }

    @Override
    public CommandResult getUser(int id) throws DataConnectionException {
        Connection conn = null;
        LinkedList<Model> users = new LinkedList<>();
        CommandResult result;

        try {
            conn = getDataConnection().getConnection();
            final String query = "select id, name, email from users where uid = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)));
            }

            rs.close();
            stmt.close();
            result = new CommandResult(users, users.size());
            rollbackConnection(conn);
        } catch (Exception e) {
            rollbackConnection(conn);
            throw new DataConnectionException("Unable to get details for user: "
                    + id + "\n" + e.getMessage());
        } finally {
            closeConnection(conn);
        }

        return result;
    }
}
