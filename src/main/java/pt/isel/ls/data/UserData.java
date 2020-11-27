package pt.isel.ls.data;

import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

public class UserData extends Data implements IUserData {
    @Override
    public LinkedList<Model> createUser(Connection connection, String name, String email)
            throws DataConnectionException {
        LinkedList<Model> users = new LinkedList<>();

        try {
            final String query = "insert into users(name, email) values(?, ?)";

            PreparedStatement stmt = connection.prepareStatement(
                    query,
                    Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, name);
            stmt.setString(2, email);

            final int status = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                User user = new User(rs.getInt(1), name, email);
                users.add(user);
            }

            stmt.close();
        } catch (Exception e) {
            throw new DataConnectionException("Unable to add User\n"
                    + e.getMessage());
        }

        return users;
    }

    @Override
    public LinkedList<Model> getAllUsers(Connection connection)
            throws DataConnectionException {
        LinkedList<Model> users = new LinkedList<>();

        try {
            final String query = "select uid, name from users;";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(
                        new User(rs.getInt(1), rs.getString(2))
                );
            }

            stmt.close();
        } catch (Exception e) {
            throw new DataConnectionException("Unable to get a list of all the users\n"
                    + e.getMessage());
        }

        return users;
    }

    @Override
    public LinkedList<Model> getUser(Connection connection, int id)
            throws DataConnectionException {
        LinkedList<Model> users = new LinkedList<>();

        try {
            final String query = "select uid, name, email from users where uid = ?;";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)));
            }

            stmt.close();
        } catch (Exception e) {
            throw new DataConnectionException("Unable to get details for user: "
                    + id + "\n" + e.getMessage());
        }

        return users;
    }
}
