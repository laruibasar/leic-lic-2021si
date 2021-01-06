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
import java.util.LinkedList;

public class UserReviewData extends Data implements IUserReviewData {
    @Override
    public LinkedList<Model> getUserReview(Connection connection, int userId, int review)
            throws DataConnectionException {
        LinkedList<Model> reviews = new LinkedList<>();

        try {
            final String query = "select summary, completeReview, rating, movie "
                + "from reviews where movieCritic = ? and rid = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, userId);
            stmt.setInt(2, review);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                Movie movie = new Movie();
                movie.setId(rs.getInt(4));
                user.setId(userId);
                reviews.add(
                        new Review(
                            review,
                            rs.getString(1),
                            rs.getString(2),
                            rs.getInt(3),
                            movie,
                            user
                ));
            }

            stmt.close();
        } catch (Exception e) {
            throw new DataConnectionException("Unable to get review " + review
                + " from user " + userId + "\n" + e.getMessage(), e);
        }

        return reviews;
    }

    @Override
    public LinkedList<Model> getUserAllReview(Connection connection, int userId)
            throws DataConnectionException {
        LinkedList<Model> reviews = new LinkedList<>();

        try {
            final String query = "select rid, summary, completeReview, rating, movie "
                    + "from reviews where movieCritic = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                Movie movie = new Movie();
                movie.setId(rs.getInt(5));
                user.setId(userId);
                reviews.add(
                        new Review(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            movie,
                            user));
            }

            stmt.close();
        } catch (Exception e) {
            throw new DataConnectionException("Unable to get reviews "
                    + " from user " + userId + "\n" + e.getMessage(), e);
        }

        return reviews;
    }
}
