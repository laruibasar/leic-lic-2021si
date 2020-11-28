package pt.isel.ls.data;

import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;

import java.sql.Connection;
import java.util.LinkedList;

public interface IMovieReviewData {
    public LinkedList<Model> createMovieReview(Connection connection, Review review)
            throws DataConnectionException;

    public LinkedList<Model> getMovieReview(Connection connection, int movie, int review)
            throws DataConnectionException;

    public LinkedList<Model> getAllMovieReviews(Connection connection, int movie)
            throws DataConnectionException;

    public LinkedList<Model> DeleteMovieReview(Connection connection, int movie, int review)
            throws DataConnectionException;
}
