package pt.isel.ls.services;

import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.SQLException;

/**
 * GET /users/{uid}/reviews/{rid} - returns the full information for the review rid made by the user identified by uid.
 */

public class GetUserReviewHandler extends Handler implements IHandler {

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        return null;
    }
}
