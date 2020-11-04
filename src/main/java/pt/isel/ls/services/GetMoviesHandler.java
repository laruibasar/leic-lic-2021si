package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.EmptyResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedList;
import java.sql.SQLException;

/**
 * GET /movies - returns a list with all movies.
 */

public class GetMoviesHandler extends Handler implements IHandler {

    private LinkedList<Model> movies = new LinkedList<>();
    private LinkedList<String> tuple = new LinkedList<>();

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException, EmptyResult {
        Data mapper = new Data();
        Connection conn = null;
        try {
            conn = mapper.getDataConnection().getConnection();
            final String query = "select mid, name from movies;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    tuple.add(rs.getString(i));
                }
                movies.add(new Movie(Integer.parseInt(tuple.get(0)),columnsNumber));
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

        return new CommandResult(movies);
    }
}
