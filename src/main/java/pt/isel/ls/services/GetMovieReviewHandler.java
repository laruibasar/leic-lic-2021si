package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.sql.SQLException;

/**
 * GET /movies/{mid}/reviews/{rid} - returns the full information for the review rid of the movie identified by mid.
 */

public class GetMovieReviewHandler extends Handler implements IHandler {

    private LinkedList<Model> reviews = new LinkedList<>();

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        Data mapper = new Data();
        Connection conn = null;
        try {
            conn = mapper.getDataConnection().getConnection();
            final String query = "select * from reviews where rid = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,Integer.parseInt(cmd.getPath().getPath().get(2)));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                reviews.add(new Review(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
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

            throw new DataConnectionException("Unable to get a list of all the movies\n"
                    + e.getMessage(), e);
        } finally {
            mapper.closeConnection(conn);
        }

        return new CommandResult(reviews,reviews.size());
    }
}
