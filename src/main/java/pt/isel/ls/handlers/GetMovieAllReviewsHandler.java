package pt.isel.ls.handlers;

import pt.isel.ls.data.IMovieReviewData;
import pt.isel.ls.data.MovieReviewData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.model.Model;
import pt.isel.ls.utils.Command;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.results.GetMovieAllReviewsResult;

import java.util.LinkedList;

/**
 * GET /movies/{mid}/reviews - returns the reviews identified by mid.
 * The information must not include the full review.
 */
public class GetMovieAllReviewsHandler extends Handler implements IHandler {
    IMovieReviewData reviewData;

    public GetMovieAllReviewsHandler() {
        super();
        reviewData = new MovieReviewData();
        description = "Return the reviews identified by mid";

        validValues.add("mid");
    }

    public void setReviewDataConnection(IMovieReviewData reviewData) {
        this.reviewData = reviewData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        String check = checkNeededValues(cmd);
        if (check.length() > 0) {
            throw new HandlerException("Handler missing parameters: "
                    + check);
        }

        int movie;
        try {
            movie = Integer.parseInt(cmd.getValue("mid"));
        } catch (NumberFormatException e) {
            throw new HandlerException("Handler invalid format for mid: "
                    + cmd.getValue("mid"));
        }

        try {
            LinkedList<Model> result = ts.executeTransaction((connection) -> {
                return reviewData.getAllMovieReviews(connection, movie);
            });

            return new GetMovieAllReviewsResult(result);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
