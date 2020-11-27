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
    public CommandResult createMovieReview(Review review)
            throws DataConnectionException {
        CommandResult result = null;
        Connection conn = null;
        LinkedList<Model> reviews = new LinkedList<>();

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
            stmt.setInt(5, review.getMovieCritic());

            final int status = stmt.executeUpdate();
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
    public CommandResult getMovieReview(int movie, int review)
            throws DataConnectionException {
        CommandResult result = null;
        Connection conn = null;
        LinkedList<Model> reviews = new LinkedList<>();

        try {
            conn = getDataConnection().getConnection();

            final String query = "select rid, summary, completeReview, rating, "
                    + "movie, movieCritic from reviews where movie = ? and rid = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, movie);
            stmt.setInt(2, review);

            ResultSet rs = stmt.executeQuery();
            if (rs.first()) {
                reviews.add(
                        new Review(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getInt(5),
                            rs.getInt(6)));
            }

            rs.close();
            stmt.close();
            conn.commit();
            result = new CommandResult(reviews, reviews.size());
        } catch (Exception e) {
            rollbackConnection(conn);
            throw new DataConnectionException("Unable to get the review:"
                    + review + " for movie: " + movie + "\n"
                    + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }

        return result;
    }

    @Override
    public CommandResult getAllMovieReviews(int movie)
            throws DataConnectionException {
        CommandResult result = null;
        Connection conn = null;
        LinkedList<Model> reviews = new LinkedList<>();

        try {
            conn = getDataConnection().getConnection();

            final String query = "select rid, summary, rating, "
                    + "movie, movieCritic from reviews where movie = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, movie);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reviews.add(
                        new Review(
                                rs.getInt(1),
                                rs.getString(2),
                                null, //rs.getString(3), we don't need
                                rs.getInt(4),
                                rs.getInt(5),
                                rs.getInt(6)));
            }

            rs.close();
            stmt.close();
            conn.commit();
            result = new CommandResult(reviews, reviews.size());
        } catch (Exception e) {
            rollbackConnection(conn);
            throw new DataConnectionException("Unable to get reviews "
                    + " for movie: " + movie + "\n"
                    + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }

        return result;
    }
}
