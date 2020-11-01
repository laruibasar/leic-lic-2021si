package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * POST /users - creates a new user, given the following parameters
 *  name - the user's name
 *  email - the user's unique email.
 */

public class CreateUserHandler extends Handler implements IHandler {

    public CreateUserHandler() {
        super();
        template.setParameters(new Parameters(new String[]{"name", "email"}));
    }

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        Data mapper = new Data();
        CommandResult cr;
        Connection conn = null;
        try {
            conn = mapper.getDataConnection().getConnection();
            final String query = "insert into users(fname,lname,email) values(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            String[] nameSplit = cmd.getParameters().getValue("name").split("\\+");
            pstmt.setString(1, nameSplit[0]);
            pstmt.setString(2, nameSplit[1]);
            pstmt.setString(3, cmd.getParameters().getValue("email"));
            ResultSet rs = pstmt.executeQuery();
            cr = new CommandResult(rs);
            conn.commit();
        } catch (Exception e) {
            if(conn != null)
                conn.rollback();
            throw new DataConnectionException("Unable to add User\n"
                    + e.getMessage(), e);
        }
        mapper.closeConnection(conn);
        return cr;
    }
}
