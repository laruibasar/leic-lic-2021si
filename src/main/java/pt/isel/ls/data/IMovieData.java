package pt.isel.ls.data;

import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;

import java.sql.Connection;
import java.util.LinkedList;

public interface IMovieData {
    public LinkedList<Model> createMovie(Connection connection, String title, int year)
            throws DataConnectionException;

    public LinkedList<Model> createMovieDetail(Connection connection, int mid,
                                               String genre, String directors, String actors)
            throws DataConnectionException;

    public LinkedList<Model> getAllMovies(Connection connection) throws DataConnectionException;

    public LinkedList<Model> getMovie(Connection connection, int id) throws DataConnectionException;

    public LinkedList<Model> getMovieDetail(Connection connection, int id) throws DataConnectionException;
}
