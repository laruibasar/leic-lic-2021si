package pt.isel.ls.data;

import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;

import java.sql.Connection;
import java.util.LinkedList;

public interface IRatingData {
    public LinkedList<Model> createRating(Connection connection, int movie, int rate)
            throws DataConnectionException;

    public LinkedList<Model> getRatings(Connection connection, int movie)
            throws DataConnectionException;
}
