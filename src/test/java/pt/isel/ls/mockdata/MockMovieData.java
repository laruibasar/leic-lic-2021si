package pt.isel.ls.mockdata;

import pt.isel.ls.data.IMovieData;
import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.utils.CommandResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

public class MockMovieData extends Data implements IMovieData {
    @Override
    public CommandResult createMovie(String title, int year) throws DataConnectionException {
        Connection conn = null;
        CommandResult result = null;
        LinkedList<Model> movies = new LinkedList<>();
        Movie movie = new Movie(title, year);

        try {
            conn = getDataConnection().getConnection();

            final String query = "insert into movies (title, year) values(?, ?)";
            PreparedStatement stmt = conn.prepareStatement(
                    query,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, title);
            stmt.setInt(2, year);

            final int status = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // move to column
            movie.setId(rs.getInt(1));
            movies.add(movie);

            rs.close();
            stmt.close();
            rollbackConnection(conn);
            result = new CommandResult(movies, status);
        } catch (Exception e) {
            rollbackConnection(conn);
            throw new DataConnectionException("Unable to add movie: " + title
                    + " from " + year + "\n" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }

        return result;
    }

    @Override
    public CommandResult getAllMovies() throws DataConnectionException {
        Connection conn = null;
        CommandResult result = null;
        LinkedList<Model> movies = new LinkedList<>();

        try {
            conn = getDataConnection().getConnection();

            final String query = "select mid, title, year from movies;";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)));
            }

            rs.close();
            stmt.close();
            rollbackConnection(conn);
            result = new CommandResult(movies, movies.size());
        } catch (Exception e) {
            rollbackConnection(conn);
            throw new DataConnectionException("Unable to get a list of all the movies\n"
                    + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }

        return result;
    }

    @Override
    public CommandResult getMovie(int id) throws DataConnectionException {
        Connection conn = null;
        CommandResult result = null;
        LinkedList<Model> movies = new LinkedList<>();

        try {
            conn = getDataConnection().getConnection();

            final String query = "select mid, title, year from movies where mid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            Movie movie = null;
            if (rs.first()) {
                movie = new Movie(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3));
            }

            rs.close();
            stmt.close();
            rollbackConnection(conn);
            if (movie != null) {
                movies.add(movie);
            }
            result = new CommandResult(movies, movies.size());
        } catch (Exception e) {
            rollbackConnection(conn);
            throw new DataConnectionException("Unable to get details from movie "
                    + id + "\n" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }

        return result;
    }
}