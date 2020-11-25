package pt.isel.ls.handlers;

import pt.isel.ls.data.IUserReviewData;
import pt.isel.ls.data.UserReviewData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
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
        description = "Return the full information for the"
                + " review rid made by the user identified by uid";

        validValues.add("uid");
        validValues.add("rid");
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

        int review;
        try {
            review = Integer.parseInt(cmd.getValue("rid"));
        } catch (NumberFormatException e) {
            throw new HandlerException("Handler invalid format for uid: "
                    + cmd.getValue("uid"));
        }

        try {
            return reviewData.getUserReview(user, review);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
