package pt.isel.ls.data;

import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import pt.isel.ls.utils.CommandResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

public class MovieReviewData extends Data implements IMovieReviewData {
    @Override
    public CommandResult createMovieReview(Review review) throws DataConnectionException {
        CommandResult result = null;
        Connection conn = null;
        LinkedList<Model> reviews = new LinkedList<Model>();

        try {
            conn = getDataConnection().getConnection();

            final String query = "insert into reviews "
                    + "(summary, completeReview, rating, movie, movieCritic) "
                    + "values(?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(
                    query,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, review.getSummary());
            stmt.setString(2, review.getCompleteReview());
            stmt.setInt(3, review.getMovie());
            stmt.setInt(4, review.getRating());

            int status = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.first()) {
                review.setId(rs.getInt(1));
            }
            reviews.add(review);

            rs.close();
            stmt.close();
            conn.commit();
            result = new CommandResult(reviews, status);
        } catch (Exception e) {
            rollbackConnection(conn);
            throw new DataConnectionException("Unable to add review to movie"
                + review.getMovie() + "\n" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }

        return result;
    }

    @Override
    public CommandResult getMovieReview(int movie, int review) throws DataConnectionException {
        return null;
    }

    @Override
    public CommandResult getAllMovieReviews(int movie) throws DataConnectionException {
        return null;
    }
}
