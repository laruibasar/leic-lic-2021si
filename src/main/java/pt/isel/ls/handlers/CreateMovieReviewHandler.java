package pt.isel.ls.handlers;

import pt.isel.ls.data.IMovieReviewData;
import pt.isel.ls.data.MovieReviewData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Review;
import pt.isel.ls.services.common.Handler;
import pt.isel.ls.services.common.HandlerException;
import pt.isel.ls.services.common.IHandler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;

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
        template.setParameters(
                new Parameters(new String[]{"uid", "reviewSummary", "review", "rating"}));
    }

    public void setReviewDataConnection(IMovieReviewData reviewData) {
        this.reviewData = reviewData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        if (!template.getParameters().isValid(cmd.getParameters())) {
            StringBuilder keys = new StringBuilder("Missing ");
            for (String str : template.getParameters()) {
                if (cmd.getParameters().getValue(str) == null) {
                    keys.append("\"").append(str).append("\" ");
                }
            }
            throw new HandlerException("Handler: missing parameters: "
                    + keys.toString());
        }

        Review review = new Review(
                template.getParameters().getValue("summary").replace("+", " "),
                template.getParameters().getValue("reviewSummary").replace("+", " "),
                Integer.parseInt(template.getPath().getValue(1)),
                Integer.parseInt(template.getParameters().getValue("uid")),
                Integer.parseInt(template.getParameters().getValue("rating"))
        );

        try {
            return reviewData.createMovieReview(review);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
