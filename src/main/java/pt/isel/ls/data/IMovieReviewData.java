package pt.isel.ls.data;

import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Review;
import pt.isel.ls.utils.CommandResult;

public interface IMovieReviewData {
    public CommandResult createMovieReview(Review review) throws DataConnectionException;

    public CommandResult getMovieReview(int movie, int review) throws DataConnectionException;

    public CommandResult getAllMovieReviews(int movie) throws DataConnectionException;
}
