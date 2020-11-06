package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * POST /movies/{mid}/reviews - creates a new review for the movie identified by mid, given the following parameters
 *
 * uid - user identifier
 * reviewSummary - the review summary
 * review - the complete review
 * rating - the review rating
 */

public class CreateMovieReviewHandler extends Handler implements IHandler {

    private LinkedList<Model> reviews = new LinkedList<>();

    public CreateMovieReviewHandler() {
        super();
        template.setParameters(new Parameters(new String[]{"uid", "reviewSummary", "review", "rating"}));
    }


    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        CommandResult result;
        Connection conn = null;
        try {
            conn = Data.getDataConnection().getConnection();
            final String query = "insert into reviews(summary,completeReview,rating,movie,movieCritic) "
                    +
                    "values(?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            final String summary = cmd.getParameters().getValue("reviewSummary");
            final String completeReview = cmd.getParameters().getValue("review");
            final int rating = Integer.parseInt(cmd.getParameters().getValue("rating"));
            final int movie = Integer.parseInt(cmd.getPath().getPath().get(1));
            final int movieCritic = Integer.parseInt(cmd.getParameters().getValue("uid"));
            pstmt.setString(1, summary);
            pstmt.setString(2, completeReview);
            pstmt.setInt(3, rating);
            pstmt.setInt(4, movie);
            pstmt.setInt(5, movieCritic);
            int status = pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            Review review = new Review(rs.getInt(1), summary,completeReview,rating,movie,movieCritic);
            reviews.add(review);
            result = new CommandResult(reviews,status);
            conn.commit();
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }

            throw new DataConnectionException("Unable to add review to the movie\n"
                    + e.getMessage(), e);
        } finally {
            Data.closeConnection(conn);
        }

        return result;
    }
}
