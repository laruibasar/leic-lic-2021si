package pt.isel.ls.data;

import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

public class MovieData extends Data implements IMovieData {
    @Override
    public LinkedList<Model> createMovie(Connection connection, String title, int year)
            throws DataConnectionException {
        LinkedList<Model> movies = new LinkedList<>();
        Movie movie = new Movie(title, year);

        try {
            final String query = "insert into movies (title, year) values(?, ?)";
            PreparedStatement stmt = connection.prepareStatement(
                    query,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, title);
            stmt.setInt(2, year);

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                movie.setId(rs.getInt(1));
                movies.add(movie);
            }

            stmt.close();
        } catch (Exception e) {
            throw new DataConnectionException("Unable to add movie: " + title
                + " from " + year + "\n" + e.getMessage(), e);
        }

        return movies;
    }

    @Override
    public LinkedList<Model> createMovieDetail(Connection connection, int mid,
                                               String genre, String directors, String actors)
            throws DataConnectionException {
        LinkedList<Model> movies = new LinkedList<>();
        Movie movie = new Movie();

        genre = (genre != null) ? genre : "";
        directors = (directors != null) ? directors : "";
        actors = (actors != null) ? actors : "";

        try {
            final String query = "insert into movie_details (mid, genre, directors, actors)"
                    + " values(?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(
                    query,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, mid);
            stmt.setString(2, genre);
            stmt.setString(3, directors);
            stmt.setString(4, actors);

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                movie.setId(rs.getInt(1));
                movie.setGenre(genre);
                movie.setDirectors(directors);
                movie.setActors(actors);

                movies.add(movie);
            }

            stmt.close();
        } catch (Exception e) {
            throw new DataConnectionException("Unable to add details to movie: "
                    + mid + "\n" + e.getMessage(), e);
        }

        return movies;
    }

    @Override
    public LinkedList<Model> getAllMovies(Connection connection) throws DataConnectionException {
        LinkedList<Model> movies = new LinkedList<>();

        try {
            final String query = "select mid, title, year from movies;";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)));
            }

            stmt.close();
        } catch (Exception e) {
            throw new DataConnectionException("Unable to get a list of all the movies\n"
                + e.getMessage(), e);
        }

        return movies;
    }

    @Override
    public LinkedList<Model> getMovie(Connection connection, int id) throws DataConnectionException {
        LinkedList<Model> movies = new LinkedList<>();

        try {
            final String query = "select mid, title, year from movies where mid = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            Movie movie = null;
            if (rs.next()) {
                movie = new Movie(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3));
            }

            stmt.close();
            if (movie != null) {
                movies.add(movie);
            }
        } catch (Exception e) {
            throw new DataConnectionException("Unable to get details from movie "
                    + id + "\n" + e.getMessage(), e);
        }

        return movies;
    }

    @Override
    public LinkedList<Model> getMovieDetail(Connection connection, int id) throws DataConnectionException {
        LinkedList<Model> movies = new LinkedList<>();
        Movie movie = new Movie();

        try {
            final String queryDetails = "select genre, director, actors "
                    + "from movie_details where mid = ?";
            PreparedStatement stmt = connection.prepareStatement(queryDetails);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                movie.setId(id);
                movie.setGenre(rs.getString(1));
                movie.setDirectors(rs.getString(2));
                movie.setActors(rs.getString(3));
            }

            stmt.close();
        } catch (Exception e) {
            throw new DataConnectionException("Unable to get details from movie "
                    + id + "\n" + e.getMessage(), e);
        }

        return movies;
    }
}
