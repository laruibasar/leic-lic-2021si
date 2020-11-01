package pt.isel.ls.services;

import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;

/**
 * GET /tops/ratings - returns a list with the movies, given the following parameters:
 *
 * n - max number of movies to list;
 * average - two possible values:
 * highest- movies with the highest average ratings
 * lowest- movies with the lowest average ratings
 * min - minimum number of votes
 */

public class GetTopRatingsHandler extends Handler implements IHandler {

    public GetTopRatingsHandler() {
        super();
        template.setParameters(new Parameters(new String[]{"n", "average", "min"}));
    }

    @Override
    public CommandResult execute(Command cmd) {
        return null;
    }
}
