package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.MovieRating;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.sql.SQLException;

/**
 * GET /movies/{mid}/ratings - returns the rating information for the movie identified by mid. This include:
 *
 * The rating average
 * The number of votes for each rating value
 */

public class GetMovieRatingHandler extends Handler implements IHandler {

    private LinkedList<Model> ratings = new LinkedList<>();

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        Connection conn = null;
        try {
            conn = Data.getDataConnection().getConnection();
            final String query = "\\set movieId ?\n"
                    +
                    "select\n"
                    +
                    "\tavg(rating)::numeric(3,2) as average,\n"
                    +
                    "\t(select count(rating) from(select rating\n"
                    +
                    "\t\tfrom ratings\n"
                    +
                    "\t\twhere movie = :movieId\n"
                    +
                    "\t\tunion all\n"
                    +
                    "\t\tselect rating\n"
                    +
                    "\t\tfrom reviews\n"
                    +
                    "\t\twhere movie = :movieId) as rating where rating = 1) as votesOne ,\n"
                    +
                    "\t(select count(rating) from(select rating\n"
                    +
                    "\t\tfrom ratings\n"
                    +
                    "\t\twhere movie = :movieId\n"
                    +
                    "\t\tunion all\n"
                    +
                    "\t\tselect rating\n"
                    +
                    "\t\tfrom reviews\n"
                    +
                    "\t\twhere movie = :movieId) as rating where rating = 2) as votesTwo ,\n"
                    +
                    "\t(select count(rating) from(select rating\n"
                    +
                    "\t\tfrom ratings\n"
                    +
                    "\t\twhere movie = :movieId\n"
                    +
                    "\t\tunion all\n"
                    +
                    "\t\tselect rating\n"
                    +
                    "\t\tfrom reviews\n"
                    +
                    "\t\twhere movie = :movieId) as rating where rating = 3) as votesThree,\n"
                    +
                    "\t(select count(rating) from(select rating\n"
                    +
                    "\t\tfrom ratings\n"
                    +
                    "\t\twhere movie = :movieId\n"
                    +
                    "\t\tunion all\n"
                    +
                    "\t\tselect rating\n"
                    +
                    "\t\tfrom reviews\n"
                    +
                    "\t\twhere movie = :movieId) as rating where rating = 4) as votesFour,\n"
                    +
                    "\t(select count(rating) from(select rating\n"
                    +
                    "\t\tfrom ratings\n"
                    +
                    "\t\twhere movie = :movieId\n"
                    +
                    "\t\tunion all\n"
                    +
                    "\t\tselect rating\n"
                    +
                    "\t\tfrom reviews\n"
                    +
                    "\t\twhere movie = :movieId) as rating where rating = 5) as votesFive\n"
                    +
                    "from (select rating\n"
                    +
                    "\t  from ratings\n"
                    +
                    "\t  where movie = :movieId\n"
                    +
                    "\t  union all\n"
                    +
                    "\t  select rating\n"
                    +
                    "\t  from reviews\n"
                    +
                    "\t  where movie = :movieId) as rating;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            int movie = Integer.parseInt(cmd.getPath().getValue(1));
            pstmt.setInt(1, movie);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ratings.add(new MovieRating(
                        movie,
                        rs.getFloat(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6)));
            }
            conn.commit();
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw new DataConnectionException("Unable to get the movie rating indicated\n"
                    + e.getMessage(), e);
        } finally {
            Data.closeConnection(conn);
        }

        return new CommandResult(ratings,ratings.size());
    }
}


