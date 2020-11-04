package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.Rating;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.*;
import java.util.LinkedList;

/**
 * GET /movies/{mid}/ratings - returns the rating information for the movie identified by mid. This rating information include:
 *
 * The rating average
 * The number of votes for each rating value
 */

public class GetMovieRatingHandler extends Handler implements IHandler {

    private LinkedList<Rating> ratings = new LinkedList<>();
    private LinkedList<String> tuple = new LinkedList<>();
    private final String query = "\\set movieId ?\n" +
            "select\n" +
            "\tavg(rating)::numeric(3,2) as average,\n" +
            "\t(select count(rating) from(select rating\n" +
            "\t\tfrom ratings\n" +
            "\t\twhere movie = :movieId\n" +
            "\t\tunion all\n" +
            "\t\tselect rating\n" +
            "\t\tfrom reviews\n" +
            "\t\twhere movie = :movieId) as rating where rating = 1) as votesOne ,\n" +
            "\t(select count(rating) from(select rating\n" +
            "\t\tfrom ratings\n" +
            "\t\twhere movie = :movieId\n" +
            "\t\tunion all\n" +
            "\t\tselect rating\n" +
            "\t\tfrom reviews\n" +
            "\t\twhere movie = :movieId) as rating where rating = 2) as votesTwo ,\n" +
            "\t(select count(rating) from(select rating\n" +
            "\t\tfrom ratings\n" +
            "\t\twhere movie = :movieId\n" +
            "\t\tunion all\n" +
            "\t\tselect rating\n" +
            "\t\tfrom reviews\n" +
            "\t\twhere movie = :movieId) as rating where rating = 3) as votesThree,\n" +
            "\t(select count(rating) from(select rating\n" +
            "\t\tfrom ratings\n" +
            "\t\twhere movie = :movieId\n" +
            "\t\tunion all\n" +
            "\t\tselect rating\n" +
            "\t\tfrom reviews\n" +
            "\t\twhere movie = :movieId) as rating where rating = 4) as votesFour,\n" +
            "\t(select count(rating) from(select rating\n" +
            "\t\tfrom ratings\n" +
            "\t\twhere movie = :movieId\n" +
            "\t\tunion all\n" +
            "\t\tselect rating\n" +
            "\t\tfrom reviews\n" +
            "\t\twhere movie = :movieId) as rating where rating = 5) as votesFive\n" +
            "from (select rating\n" +
            "\t  from ratings\n" +
            "\t  where movie = :movieId\n" +
            "\t  union all\n" +
            "\t  select rating\n" +
            "\t  from reviews\n" +
            "\t  where movie = :movieId) as rating;";
    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        Data mapper = new Data();
        Connection conn = null;
        try {
            conn = mapper.getDataConnection().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, cmd.getPath().getPath().get(1));
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()){
                for (int i = 1; i <= columnsNumber; i++) {
                    tuple.add(rs.getString(i));
                }
                ratings.add(new Rating(
                        Float.parseFloat(tuple.get(0)),
                        Integer.parseInt(tuple.get(1)),
                        Integer.parseInt(tuple.get(2)),
                        Integer.parseInt(tuple.get(3)),
                        Integer.parseInt(tuple.get(4)),
                        Integer.parseInt(tuple.get(5))));
                tuple.clear();
            }
            conn.commit();
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            if(conn != null)
                conn.rollback();
            throw new DataConnectionException("Unable to get a list of all the movies\n"
                    + e.getMessage(), e);
        }finally {
            mapper.closeConnection(conn);
        }

        return new CommandResult(ratings);
    }
}


