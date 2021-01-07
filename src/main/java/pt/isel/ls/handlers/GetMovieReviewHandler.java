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
import pt.isel.ls.results.GetMovieReviewResult;

import java.util.LinkedList;

/**
 * GET /movies/{mid}/reviews/{rid} - returns the full information for the
 * review rid of the movie identified by mid.
 */
public class GetMovieReviewHandler extends Handler implements IHandler {
    IMovieReviewData reviewData;

    public GetMovieReviewHandler() {
        super();
        reviewData = new MovieReviewData();
        description = "Return the rating information for the movie identified by mid";

        validValues.add("mid");
        validValues.add("rid");
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


        int review;
        try {
            review = Integer.parseInt(cmd.getValue("rid"));
        } catch (NumberFormatException e) {
            throw new HandlerException("Handler invalid format for rid: "
                    + cmd.getValue("rid"));
        }

        try {
            LinkedList<Model> result = ts.executeTransaction((connection) -> {
                return reviewData.getMovieReview(connection, movie, review);
            });

            return new GetMovieReviewResult(result);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
