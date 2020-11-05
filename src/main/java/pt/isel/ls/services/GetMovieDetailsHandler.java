package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.sql.SQLException;


/**
 * GET /movies/{mid} - returns the detailed information for the movie identified by mid
 */

public class GetMovieDetailsHandler extends Handler implements IHandler {

    private LinkedList<Model> movies = new LinkedList<>();

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        Data mapper = new Data();
        Connection conn = null;
        try {
            conn = mapper.getDataConnection().getConnection();
            final String query = "select * from movies where mid = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, cmd.getPath().getPath().get(1));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)));
            }

            conn.commit();
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw new DataConnectionException("Unable to get information of the movie\n"
                    + e.getMessage(), e);
        } finally {
            mapper.closeConnection(conn);
        }

        return new CommandResult(movies,movies.size());
    }
}
