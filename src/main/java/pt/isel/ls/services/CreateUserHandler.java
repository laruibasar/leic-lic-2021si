package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.User;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * POST /users - creates a new user, given the following parameters
 *  name - the user's name
 *  email - the user's unique email.
 */

public class CreateUserHandler extends Handler implements IHandler {

    private LinkedList<Model> users = new LinkedList<>();

    public CreateUserHandler() {
        super();
        template.setParameters(new Parameters(new String[]{"name", "email"}));
    }

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        Data mapper = new Data();
        CommandResult result;
        Connection conn = null;
        try {
            conn = mapper.getDataConnection().getConnection();
            final String query = "insert into users(name,email) values(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            final String name = cmd.getParameters().getValue("name");
            final String email = cmd.getParameters().getValue("email");
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            int status = pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            User user = new User(rs.getInt(1),name,email);
            users.add(user);
            result = new CommandResult(users,status);
            conn.commit();
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }

            throw new DataConnectionException("Unable to add User\n"
                    + e.getMessage(), e);
        } finally {
            mapper.closeConnection(conn);
        }

        return result;
    }
}
