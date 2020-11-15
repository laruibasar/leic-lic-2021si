package pt.isel.ls.data;

import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.MovieRating;
import pt.isel.ls.model.Rating;
import pt.isel.ls.utils.CommandResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

public class RatingData extends Data implements IRatingData {
    @Override
    public CommandResult createRating(int movie, int rate)
            throws DataConnectionException {
        CommandResult result = null;
        Connection conn = null;
        LinkedList<Model> ratings = new LinkedList<>();

        try {
            conn = getDataConnection().getConnection();

            final String query = "insert into ratings(mid, rating) values (?, ?)";

            PreparedStatement stmt = conn.prepareStatement(
                    query,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, movie);
            stmt.setInt(2, rate);

            int status = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.first()) {
                ratings.add(new Rating(rs.getInt(1), movie, rate));
            }

            rs.close();
            stmt.close();
            conn.commit();
            result = new CommandResult(ratings, status);
        } catch (Exception e) {
            rollbackConnection(conn);
            throw new DataConnectionException("Unable to add rating: " + rate
                + " to movie " + movie + "\n"
                + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }

        return result;
    }

    @Override
    public CommandResult getRatings(int movie) throws DataConnectionException {
        CommandResult result = null;
        Connection conn = null;
        LinkedList<Model> ratings = new LinkedList<>();

        try {
            conn = getDataConnection().getConnection();

            final String query = "select\n"
                    + "\tavg(rating)::numeric(3,2) as average,\n"
                    + "\t(select count(rating) from(select rating\n"
                    + "\t\tfrom ratings\n"
                    + "\t\twhere movie = ?\n"
                    + "\t\tunion all\n"
                    + "\t\tselect rating\n"
                    + "\t\tfrom reviews\n"
                    + "\t\twhere movie = ?) as rating where rating = 1) as votesOne ,\n"
                    + "\t(select count(rating) from(select rating\n"
                    + "\t\tfrom ratings\n"
                    + "\t\twhere movie = ?\n"
                    + "\t\tunion all\n"
                    + "\t\tselect rating\n"
                    + "\t\tfrom reviews\n"
                    + "\t\twhere movie = ?) as rating where rating = 2) as votesTwo ,\n"
                    + "\t(select count(rating) from(select rating\n"
                    + "\t\tfrom ratings\n"
                    + "\t\twhere movie = ?\n"
                    + "\t\tunion all\n"
                    + "\t\tselect rating\n"
                    + "\t\tfrom reviews\n"
                    + "\t\twhere movie = ?) as rating where rating = 3) as votesThree,\n"
                    + "\t(select count(rating) from(select rating\n"
                    + "\t\tfrom ratings\n"
                    + "\t\twhere movie = ?\n"
                    + "\t\tunion all\n"
                    + "\t\tselect rating\n"
                    + "\t\tfrom reviews\n"
                    + "\t\twhere movie = ?) as rating where rating = 4) as votesFour,\n"
                    + "\t(select count(rating) from(select rating\n"
                    + "\t\tfrom ratings\n"
                    + "\t\twhere movie = ?\n"
                    + "\t\tunion all\n"
                    + "\t\tselect rating\n"
                    + "\t\tfrom reviews\n"
                    + "\t\twhere movie = ?) as rating where rating = 5) as votesFive\n"
                    + "from (select rating\n"
                    + "\t  from ratings\n"
                    + "\t  where movie = ?\n"
                    + "\t  union all\n"
                    + "\t  select rating\n"
                    + "\t  from reviews\n"
                    + "\t  where movie = ?) as rating;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, movie);

            ResultSet rs = stmt.executeQuery();
            if (rs.first()) {
                ratings.add(new MovieRating(
                        movie,
                        rs.getFloat(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6)));
            }

            rs.close();
            stmt.close();
            conn.commit();
            result = new CommandResult(ratings, ratings.size());
        } catch (Exception e) {
            rollbackConnection(conn);
            throw new DataConnectionException("Unable to get ratings "
                    + " to movie " + movie + "\n"
                    + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }

        return result;
    }
}
