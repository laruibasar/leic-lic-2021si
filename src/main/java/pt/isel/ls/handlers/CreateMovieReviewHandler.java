package pt.isel.ls.handlers;

import pt.isel.ls.data.IMovieReviewData;
import pt.isel.ls.data.MovieReviewData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.Review;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.model.User;
import pt.isel.ls.utils.Command;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.results.CreateMovieReviewResult;

import java.util.LinkedList;

/**
 * POST /movies/{mid}/reviews - creates a new review for the movie identified by mid, given the following parameters
 *
 * uid - user identifier
 * reviewSummary - the review summary
 * review - the complete review
 * rating - the review rating
 */
public class CreateMovieReviewHandler extends Handler implements IHandler {
    IMovieReviewData reviewData;

    public CreateMovieReviewHandler() {
        super();
        reviewData = new MovieReviewData();
        description = "Creates a new review for the movie identified by mid";

        validValues.add("uid");
        validValues.add("reviewSummary");
        validValues.add("mid");
        validValues.add("review");
        validValues.add("rating");
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

        Review review;
        try {
            User user = new User();
            Movie movie = new Movie();
            movie.setId(Integer.parseInt(cmd.getValue("mid")));
            user.setId(Integer.parseInt(cmd.getValue("uid")));
            review = new Review(
                    cmd.getValue("reviewSummary"),
                    cmd.getValue("review"),
                    movie,
                    Integer.parseInt(cmd.getValue("rating")),
                    user
            );
        } catch (Exception e) {
            throw new HandlerException("Handler invalid format in values"
                    + e.getMessage());
        }

        try {
            LinkedList<Model> result = ts.executeTransaction((connection) -> {
                return reviewData.createMovieReview(connection, review);
            });

            return new CreateMovieReviewResult(result);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
