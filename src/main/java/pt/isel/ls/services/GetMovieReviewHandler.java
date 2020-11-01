package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * GET /movies/{mid}/reviews/{rid} - returns the full information for the review rid of the movie identified by mid.
 */

public class GetMovieReviewHandler extends Handler implements IHandler {

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        Data mapper = new Data();
        CommandResult cr;
        Connection conn = null;
        try {
            conn = mapper.getDataConnection().getConnection();
            final String query = "select * from reviews where rid = ? union all select from movies where name = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(cmd.getPath().getPath().get(3)));
            pstmt.setInt(2, Integer.parseInt(cmd.getPath().getPath().get(1)));
            ResultSet rs = pstmt.executeQuery();
            cr = new CommandResult(rs);
            conn.commit();
        } catch (Exception e) {
            if(conn != null)
                conn.rollback();
            throw new DataConnectionException("Unable to add review to the movie\n"
                    + e.getMessage(), e);
        }
        mapper.closeConnection(conn);
        return cr;
    }
}
