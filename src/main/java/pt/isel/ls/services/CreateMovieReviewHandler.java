package pt.isel.ls.services;

import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;
import pt.isel.ls.utils.ParametersExceptions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
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
        template.setParameters(
                new Parameters(new String[]{"uid", "reviewSummary", "review", "rating"}));
    }

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException,
            SQLException, ParametersExceptions {
        CommandResult result;
        Connection conn = null;

        if (!template.getParameters().isValid(cmd.getParameters())) {
            StringBuilder keys = new StringBuilder("Missing ");
            for (String str : template.getParameters()) {
                if (cmd.getParameters().getValue(str) == null) {
                    keys.append("\"").append(str).append("\" ");
                }
            }
            throw new ParametersExceptions(keys.toString());
        }

        try {
            conn = Data.getDataConnection().getConnection();
            final String query = "insert into reviews "
                    + "(summary, completeReview, rating, movie, movieCritic) "
                    + "values(?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(
                    query,
                    Statement.RETURN_GENERATED_KEYS);

            final String summary = cmd
                    .getParameters()
                    .getValue("reviewSummary")
                    .replace("+", " ");
            pstmt.setString(1, summary);

            final String completeReview = cmd
                    .getParameters()
                    .getValue("review")
                    .replace("+", " ");
            pstmt.setString(2, completeReview);

            final int rating = Integer.parseInt(cmd.getParameters().getValue("rating"));
            pstmt.setInt(3, rating);

            final int movie = Integer.parseInt(cmd.getPath().getValue(1));
            pstmt.setInt(4, movie);

            final int movieCritic = Integer.parseInt(cmd.getParameters().getValue("uid"));
            pstmt.setInt(5, movieCritic);

            int status = pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next(); // move to column
            Review review = new Review(rs.getInt(1),
                    completeReview, summary, movie, rating, movieCritic);
            reviews.add(review);
            result = new CommandResult(reviews,status);

            rs.close();
            pstmt.close();
            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw new DataConnectionException("Unable to add review to the movie\n"
                    + e.getMessage());
        } finally {
            Data.closeConnection(conn);
        }

        return result;
    }
}
