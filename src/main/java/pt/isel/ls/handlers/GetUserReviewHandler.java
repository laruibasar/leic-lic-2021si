package pt.isel.ls.handlers;

import pt.isel.ls.data.IUserReviewData;
import pt.isel.ls.data.UserReviewData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

/**
 * GET /users/{uid}/reviews/{rid} - returns the full information for the
 * review rid made by the user identified by uid.
 */
public class GetUserReviewHandler extends Handler implements IHandler {
    IUserReviewData reviewData;

    public GetUserReviewHandler() {
        super();
        reviewData = new UserReviewData();
    }

    public void setReviewDataConnection(IUserReviewData reviewData) {
        this.reviewData = reviewData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        final int user = Integer.parseInt(cmd.getPath().getValue(1));

        final int review = Integer.parseInt(cmd.getPath().getValue(2));

        try {
            return reviewData.getUserReview(user, review);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
