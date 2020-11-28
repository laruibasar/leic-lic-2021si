package pt.isel.ls.data;

import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class UserReviewData extends Data implements IUserReviewData {
    @Override
    public LinkedList<Model> getUserReview(Connection connection, int user, int review)
            throws DataConnectionException {
        LinkedList<Model> reviews = new LinkedList<>();

        try {
            final String query = "select summary, completeReview, rating, movie "
                + "from reviews where movieCritic = ? and rid = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, user);
            stmt.setInt(2, review);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                reviews.add(
                        new Review(
                            review,
                            rs.getString(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            user
                ));
            }

            stmt.close();
        } catch (Exception e) {
            throw new DataConnectionException("Unable to get review " + review
                + " from user " + user + "\n" + e.getMessage(), e);
        }

        return reviews;
    }

    @Override
    public LinkedList<Model> getUserAllReview(Connection connection, int user)
            throws DataConnectionException {
        LinkedList<Model> reviews = new LinkedList<>();

        try {
            final String query = "select rid, summary, completeReview, rating, movie "
                    + "from reviews where movieCritic = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, user);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                reviews.add(
                        new Review(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getInt(5),
                            user));
            }

            stmt.close();
        } catch (Exception e) {
            throw new DataConnectionException("Unable to get reviews "
                    + " from user " + user + "\n" + e.getMessage(), e);
        }

        return reviews;
    }
}
