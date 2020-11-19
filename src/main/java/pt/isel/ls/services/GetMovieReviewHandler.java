package pt.isel.ls.services;

import pt.isel.ls.data.IMovieReviewData;
import pt.isel.ls.data.MovieReviewData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

/**
 * GET /movies/{mid}/reviews/{rid} - returns the full information for the
 * review rid of the movie identified by mid.
 */
public class GetMovieReviewHandler extends Handler implements IHandler {
    IMovieReviewData reviewData;

    public GetMovieReviewHandler() {
        super();
        reviewData = new MovieReviewData();
    }

    public void setReviewDataConnection(IMovieReviewData reviewData) {
        this.reviewData = reviewData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        final int movie = Integer.parseInt(cmd.getPath().getValue(1));
        final int review = Integer.parseInt(cmd.getPath().getValue(2));

        try {
            return reviewData.getMovieReview(movie, review);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
