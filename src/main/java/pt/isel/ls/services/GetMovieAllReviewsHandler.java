package pt.isel.ls.services;

import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.sql.SQLException;


/**
 * GET /movies/{mid}/reviews - returns the reviews identified by mid. The information must not include the full review.
 */

public class GetMovieAllReviewsHandler extends Handler implements IHandler {

    private LinkedList<Model> reviews = new LinkedList<>();

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        Connection conn = null;
        try {
            conn = Data.getDataConnection().getConnection();
            final String query = "select movie, summary, rating, movieCritic from reviews where mid = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,
                    Integer.parseInt(cmd.getPath().getValue(1)));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reviews.add(new Review(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)));
            }
            conn.commit();
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw new DataConnectionException("Unable to get a list of all the movie reviews\n"
                    + e.getMessage());
        } finally {
            Data.closeConnection(conn);
        }

        return new CommandResult(reviews,reviews.size());
    }
}
