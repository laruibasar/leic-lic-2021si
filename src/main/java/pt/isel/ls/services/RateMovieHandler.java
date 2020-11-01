package pt.isel.ls.services;

import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;

/**
 * POST /movies/{mid}/ratings - submits a new rating for the movie identified by mid, given the following parameters
 *
 * rating - integer between 1 and 5.
 */

public class RateMovieHandler extends Handler implements IHandler {

    public RateMovieHandler() {
        super();
        template.setParameters(new Parameters(new String[]{"rating"}));
    }

    @Override
    public CommandResult execute(Command cmd) {
        return null;
    }
}
