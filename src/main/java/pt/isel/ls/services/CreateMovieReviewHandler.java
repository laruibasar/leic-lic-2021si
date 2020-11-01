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
 * POST /movies/{mid}/reviews - creates a new review for the movie identified by mid, given the following parameters
 *
 * uid - user identifier
 * reviewSummary - the review summary
 * review - the complete review
 * rating - the review rating
 */

public class CreateMovieReviewHandler extends Handler implements IHandler {

    public CreateMovieReviewHandler() {
        super();
        template.setParameters(new Parameters(new String[]{"uid", "reviewSummary", "review", "rating"}));
    }


    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        Data mapper = new Data();
        CommandResult cr;
        Connection conn = null;
        try {
            conn = mapper.getDataConnection().getConnection();
            final String query = "insert into reviews(summary,completeReview,rating,movie,movieCritic) values(?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, cmd.getParameters().getValue("reviewSummary"));
            pstmt.setString(2, cmd.getParameters().getValue("review"));
            pstmt.setInt(3, Integer.parseInt(cmd.getParameters().getValue("rating")));
            pstmt.setInt(4, Integer.parseInt(cmd.getPath().getPath().get(1)));
            pstmt.setInt(5, Integer.parseInt(cmd.getParameters().getValue("uid")));
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
