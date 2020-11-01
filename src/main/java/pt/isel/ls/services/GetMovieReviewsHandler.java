package pt.isel.ls.services;

import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.SQLException;


/**
 * GET /movies/{mid}/reviews - returns all the reviews for the movie identified by mid. The information for each review must not include the full review text.
 */

public class GetMovieReviewsHandler extends Handler implements IHandler {

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        return null;
    }
}
