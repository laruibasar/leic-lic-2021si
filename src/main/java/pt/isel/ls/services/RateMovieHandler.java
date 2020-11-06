package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Rating;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


/**
 * POST /movies/{mid}/ratings - submits a new rating for the movie identified by mid, given the following parameters
 *
 * rating - integer between 1 and 5.
 */

public class RateMovieHandler extends Handler implements IHandler {

    private LinkedList<Model> ratings = new LinkedList<>();

    public RateMovieHandler() {
        super();
        template.setParameters(new Parameters(new String[]{"rating"}));
    }

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        CommandResult result;
        Connection conn = null;
        try {
            conn = Data.getDataConnection().getConnection();
            final String query = "insert into ratings(movie,rating) values(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            final int mid = Integer.parseInt(cmd.getPath().getPath().get(1));
            final int rate = Integer.parseInt(cmd.getParameters().getValue("rating"));
            pstmt.setInt(1, mid);
            pstmt.setInt(2, rate);

            int status = pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            Rating rating = new Rating(rs.getInt(1),rate,mid);
            ratings.add(rating);
            result = new CommandResult(ratings,status);
            conn.commit();
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }

            throw new DataConnectionException("Unable to add rating\n"
                    + e.getMessage(), e);
        } finally {
            Data.closeConnection(conn);
        }
        return result;
    }
}
