package pt.isel.ls.services;

import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.SQLException;

/**
 * GET /users/{uid}/reviews - returns all the reviews made from the user identified by uid. The information for each review must not include the full review text.
 */

public class GetUserReviewsHandler extends Handler implements IHandler {

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        return null;
    }
}
