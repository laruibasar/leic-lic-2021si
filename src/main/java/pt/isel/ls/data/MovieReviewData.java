package pt.isel.ls.data;

import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.Review;
import pt.isel.ls.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

public class MovieReviewData extends Data implements IMovieReviewData {
    @Override
    public LinkedList<Model> createMovieReview(Connection connection, Review review)
            throws DataConnectionException {
        LinkedList<Model> reviews = new LinkedList<>();

        try {
            final String query = "insert into reviews "
                    + "(summary, completeReview, rating, movie, movieCritic) "
                    + "values(?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(
                    query,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, review.getSummary());
            stmt.setString(2, review.getCompleteReview());
            stmt.setInt(3, review.getRating());
            stmt.setInt(4, review.getMovie().getMid());
            stmt.setInt(5, review.getMovieCritic().getId());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                review.setId(rs.getInt(1));
                reviews.add(review);
            }

            stmt.close();
        } catch (Exception e) {
            throw new DataConnectionException("Unable to add review to movie"
                + review.getMovie() + "\n" + e.getMessage(), e);
        }

        return reviews;
    }

    @Override
    public LinkedList<Model> getMovieReview(Connection connection, int movieId, int review)
            throws DataConnectionException {
        LinkedList<Model> reviews = new LinkedList<>();

        try {

            final String query = "select reviews.rid, reviews.summary, reviews.completeReview, reviews.rating,"
                    + " movies.mid, movies.title, movies.year, users.uid, users.name"
                    + " from reviews"
                    + " inner join movies on reviews.movie = movies.mid"
                    + " inner join users on reviews.movieCritic = users.uid"
                    + " where rid = ? and movie = ?;";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, review);
            stmt.setInt(2, movieId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt(8), rs.getString(9));
                Movie movie = new Movie(rs.getInt(5), rs.getString(6), rs.getInt(7));
                reviews.add(
                        new Review(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            movie,
                            user
                            ));
            }

            stmt.close();
        } catch (Exception e) {
            throw new DataConnectionException("Unable to get the review:"
                    + review + " for movie: " + movieId + "\n"
                    + e.getMessage(), e);
        }

        return reviews;
    }

    @Override
    public LinkedList<Model> getAllMovieReviews(Connection connection, int movieId)
            throws DataConnectionException {
        LinkedList<Model> reviews = new LinkedList<>();

        try {

            final String query = "select rid, summary, rating,"
                    + "movies.mid, movies.title, users.name "
                    + "from reviews join movies on(reviews.movie = movies.mid) "
                    + "join users on(reviews.movieCritic = users.uid)"
                    + "where movie = ?;";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, movieId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString(6));
                Movie movie = new Movie(rs.getInt(4),rs.getString(5));
                reviews.add(
                        new Review(
                                rs.getInt(1),
                                rs.getString(2),
                                null, //rs.getString(3), we don't need
                                rs.getInt(3),
                                movie,
                                user));
            }

            stmt.close();
        } catch (Exception e) {
            throw new DataConnectionException("Unable to get reviews "
                    + " for movie: " + movieId + "\n"
                    + e.getMessage(), e);
        }

        return reviews;
    }

    @Override
    public LinkedList<Model> deleteMovieReview(Connection connection, int movieId, int review)
            throws DataConnectionException {

        LinkedList<Model> reviews = new LinkedList<>();

        try {
            final String query = "delete from reviews where movie = ? and rid = ?;";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, movieId);
            stmt.setInt(2, review);

            int rs = stmt.executeUpdate();
            Movie movie = new Movie();
            movie.setId(movieId);

            if (rs == 1) {
                reviews.add(new Review(review,movie));
            }

            stmt.close();
        } catch (Exception e) {
            throw new DataConnectionException("Unable to delete review "
                    + review
                    + " of movie"
                    + movieId + "\n" + e.getMessage(), e);
        }

        return reviews;
    }
}
