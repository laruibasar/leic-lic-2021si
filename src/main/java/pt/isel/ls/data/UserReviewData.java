package pt.isel.ls.data;

import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import pt.isel.ls.utils.CommandResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class UserReviewData extends Data implements IUserReviewData {
    @Override
    public CommandResult getUserReview(int user, int review) throws DataConnectionException {
        Connection conn = null;
        CommandResult result = null;
        LinkedList<Model> reviews = new LinkedList<>();

        try {
            conn = getDataConnection().getConnection();

            final String query = "select summary, completeReview, rating, movie "
                + "from reviews where movieCritic = ? and rid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, user);
            stmt.setInt(2, review);
            ResultSet rs = stmt.executeQuery();

            if (rs.first()) {
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

            rs.close();
            stmt.close();
            conn.commit();
            result = new CommandResult(reviews, reviews.size());
        } catch (Exception e) {
            rollbackConnection(conn);
            throw new DataConnectionException("Unable to get review " + review
                + " from user " + user + "\n" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    @Override
    public CommandResult getUserAllReview(int user) throws DataConnectionException {
        Connection conn = null;
        CommandResult result = null;
        LinkedList<Model> reviews = new LinkedList<>();

        try {
            conn = getDataConnection().getConnection();

            final String query = "select rid, summary, completeReview, rating, movie "
                    + "from reviews where movieCritic = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
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

            rs.close();
            stmt.close();
            conn.commit();
            result = new CommandResult(reviews, reviews.size());
        } catch (Exception e) {
            rollbackConnection(conn);
            throw new DataConnectionException("Unable to get reviews "
                    + " from user " + user + "\n" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
}
