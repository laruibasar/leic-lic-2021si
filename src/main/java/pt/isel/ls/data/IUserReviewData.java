package pt.isel.ls.data;

import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;

import java.sql.Connection;
import java.util.LinkedList;

public interface IUserReviewData {
    public LinkedList<Model> getUserReview(Connection connection, int user, int review)
            throws DataConnectionException;

    public LinkedList<Model> getUserAllReview(Connection connection, int user)
            throws DataConnectionException;
}
