package pt.isel.ls.handlers;

import pt.isel.ls.data.IMovieReviewData;
import pt.isel.ls.data.MovieReviewData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.services.common.Handler;
import pt.isel.ls.services.common.HandlerException;
import pt.isel.ls.services.common.IHandler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

/**
 * GET /movies/{mid}/reviews - returns the reviews identified by mid.
 * The information must not include the full review.
 */
public class GetMovieAllReviewsHandler extends Handler implements IHandler {
    IMovieReviewData reviewData;

    public GetMovieAllReviewsHandler() {
        super();
        reviewData = new MovieReviewData();
    }

    public void setReviewDataConnection(IMovieReviewData reviewData) {
        this.reviewData = reviewData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        final int movie = Integer.parseInt(cmd.getPath().getValue(1));

        try {
            return reviewData.getAllMovieReviews(movie);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
