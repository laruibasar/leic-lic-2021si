package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * GET /movies/{mid}/ratings - returns the rating information for the movie identified by mid. This rating information include:
 *
 * The rating average
 * The number of votes for each rating value
 */

public class GetMovieRatingHandler extends Handler implements IHandler {

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        Data mapper = new Data();
        CommandResult cr;
        Connection conn = null;
        try {
            conn = mapper.getDataConnection().getConnection();
            final String query = "select\n" +
                    "\tavg(rating)::numeric(3,2) as average,\n" +
                    "\t(select count(rating) from(select rating\n" +
                    "\t\tfrom ratings\n" +
                    "\t\twhere movie = ?\n" +
                    "\t\tunion all\n" +
                    "\t\tselect rating\n" +
                    "\t\tfrom reviews\n" +
                    "\t\twhere movie = ?) as rating where rating = 1) as votesOne ,\n" +
                    "\t(select count(rating) from(select rating\n" +
                    "\t\tfrom ratings\n" +
                    "\t\twhere movie = ?\n" +
                    "\t\tunion all\n" +
                    "\t\tselect rating\n" +
                    "\t\tfrom reviews\n" +
                    "\t\twhere movie = ?) as rating where rating = 2) as votesTwo ,\n" +
                    "\t(select count(rating) from(select rating\n" +
                    "\t\tfrom ratings\n" +
                    "\t\twhere movie = ?\n" +
                    "\t\tunion all\n" +
                    "\t\tselect rating\n" +
                    "\t\tfrom reviews\n" +
                    "\t\twhere movie = ?) as rating where rating = 3) as votesThree,\n" +
                    "\t(select count(rating) from(select rating\n" +
                    "\t\tfrom ratings\n" +
                    "\t\twhere movie = ?\n" +
                    "\t\tunion all\n" +
                    "\t\tselect rating\n" +
                    "\t\tfrom reviews\n" +
                    "\t\twhere movie = ?) as rating where rating = 4) as votesFour,\n" +
                    "\t(select count(rating) from(select rating\n" +
                    "\t\tfrom ratings\n" +
                    "\t\twhere movie = ?\n" +
                    "\t\tunion all\n" +
                    "\t\tselect rating\n" +
                    "\t\tfrom reviews\n" +
                    "\t\twhere movie = ?) as rating where rating = 5) as votesFive\n" +
                    "from (select rating\n" +
                    "\tfrom ratings\n" +
                    "\twhere movie = ?\n" +
                    "\tunion all\n" +
                    "\tselect rating\n" +
                    "\tfrom reviews\n" +
                    "\twhere movie = ?) as rating";
            PreparedStatement pstmt = conn.prepareStatement(query);
            for(int i = 1; i < 13; i++)
                pstmt.setString(i, cmd.getPath().getPath().get(1));
            ResultSet rs = pstmt.executeQuery();
            cr = new CommandResult(rs);
            conn.commit();
        } catch (Exception e) {
            if(conn != null)
                conn.rollback();
            throw new DataConnectionException("Unable to get a list of all the movies\n"
                    + e.getMessage(), e);
        }
        mapper.closeConnection(conn);
        return cr;
    }
}


