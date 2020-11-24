package pt.isel.ls.data;

import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.utils.CommandResult;

public interface IUserReviewData {
    public CommandResult getUserReview(int user, int review) throws DataConnectionException;

    public CommandResult getUserAllReview(int user) throws DataConnectionException;
}
