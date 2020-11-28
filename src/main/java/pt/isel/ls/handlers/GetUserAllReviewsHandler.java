package pt.isel.ls.handlers;

import pt.isel.ls.data.IUserReviewData;
import pt.isel.ls.data.UserReviewData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.model.Model;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.results.CommandResult;
import pt.isel.ls.view.results.GetUserAllReviewsResult;

import java.util.LinkedList;

/**
 * GET /users/{uid}/reviews - returns all reviews from the user identified by uid. Must not include the full review.
 */
public class GetUserAllReviewsHandler extends Handler implements IHandler {
    IUserReviewData reviewData;

    public GetUserAllReviewsHandler() {
        super();
        reviewData = new UserReviewData();
        description = "Return all reviews from the user identified by uid";

        validValues.add("uid");
    }

    public void setReviewDataConnection(IUserReviewData reviewData) {
        this.reviewData = reviewData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        String check = checkNeededValues(cmd);
        if (check.length() > 0) {
            throw new HandlerException("Handler missing parameters: "
                    + check);
        }

        int user;
        try {
            user = Integer.parseInt(cmd.getValue("uid"));
        } catch (NumberFormatException e) {
            throw new HandlerException("Handler invalid format for uid: "
                    + cmd.getValue("uid"));
        }

        try {
            LinkedList<Model> result = ts.executeTransaction((connection) -> {
                return reviewData.getUserAllReview(connection, user);
            });

            return new GetUserAllReviewsResult(result);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
