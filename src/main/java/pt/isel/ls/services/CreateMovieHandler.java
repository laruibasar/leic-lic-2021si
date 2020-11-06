package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class CreateMovieHandler extends Handler implements IHandler {

    private LinkedList<Model> movies = new LinkedList<>();

    /**
     *POST /movies - creates a new movie, given the following parameters
     *  title - the movie's name.
     *  releaseYear - the movie's release year.
     */

    public CreateMovieHandler() {
        super();
        template.setParameters(new Parameters(new String[]{"title", "releaseYear"}));
    }

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        CommandResult result;
        Connection conn = null;
        try {
            conn = Data.getDataConnection().getConnection();
            final String query = "insert into movies(title,year) values(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            final String name = cmd.getParameters().getValue("title");
            final int year = Integer.parseInt(cmd.getParameters().getValue("releaseYear"));
            pstmt.setString(1,name);
            pstmt.setInt(2, year);
            int status = pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            Movie movie = new Movie(rs.getInt(1),name,year);
            result = new CommandResult(movies,status);
            conn.commit();
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw new DataConnectionException("Unable to add movie\n"
                    + e.getMessage(), e);
        } finally {
            Data.closeConnection(conn);
        }
        return result;
    }
}
