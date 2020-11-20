package pt.isel.ls.handlers;

import pt.isel.ls.data.IUserReviewData;
import pt.isel.ls.data.UserReviewData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.services.common.Handler;
import pt.isel.ls.services.common.HandlerException;
import pt.isel.ls.services.common.IHandler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

/**
 * GET /users/{uid}/reviews - returns all reviews from the user identified by uid. Must not include the full review.
 */
public class GetUserAllReviewsHandler extends Handler implements IHandler {
    IUserReviewData reviewData;

    public GetUserAllReviewsHandler() {
        super();
        reviewData = new UserReviewData();
    }

    public void setReviewDataConnection(IUserReviewData reviewData) {
        this.reviewData = reviewData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        final int user = Integer.parseInt(cmd.getPath().getValue(1));

        try {
            return reviewData.getUserAllReview(user);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
