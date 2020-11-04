package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.Review;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.*;
import java.util.LinkedList;


/**
 * GET /movies/{mid}/reviews - returns the reviews identified by mid. The information must not include the full review.
 */

public class GetMovieReviewsHandler extends Handler implements IHandler {

    private LinkedList<Review> reviews = new LinkedList<>();
    private LinkedList<String> tuple = new LinkedList<>();
    private final String query = "select movie, summary, rating, movieCritic from reviews where mid = ?;";

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        Data mapper = new Data();
        Connection conn = null;
        try {
            conn = mapper.getDataConnection().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, cmd.getPath().getPath().get(1));
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    tuple.add(rs.getString(i));
                }
                reviews.add(new Review(
                        Integer.parseInt(tuple.get(0)),
                        tuple.get(1),
                        Integer.parseInt(tuple.get(2)),
                        Integer.parseInt(tuple.get(3))));
                tuple.clear();
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

        return new CommandResult(reviews);
    }
}
