package pt.isel.ls.data;

import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;

import java.sql.Connection;
import java.util.LinkedList;

public interface ITopRatingData {
    public LinkedList<Model> getTopRating(Connection connection, int number, int average, int min)
            throws DataConnectionException;
}
