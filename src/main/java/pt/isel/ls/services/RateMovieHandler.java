package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * POST /movies/{mid}/ratings - submits a new rating for the movie identified by mid, given the following parameters
 *
 * rating - integer between 1 and 5.
 */

public class RateMovieHandler extends Handler implements IHandler {

    private final String query = "insert into ratings(mid,rating) values(?,?)";

    public RateMovieHandler() {
        super();
        template.setParameters(new Parameters(new String[]{"rating"}));
    }

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        Data mapper = new Data();
        CommandResult result;
        Connection conn = null;
        try {
            conn = mapper.getDataConnection().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(cmd.getPath().getPath().get(1)));
            pstmt.setString(2,cmd.getParameters().getValue("rating"));
            result = new CommandResult(pstmt.executeUpdate());
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            if(conn != null)
                conn.rollback();
            throw new DataConnectionException("Unable to add movie\n"
                    + e.getMessage(), e);
        }finally {
            mapper.closeConnection(conn);
        }
        return result;
    }
}
