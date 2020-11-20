package pt.isel.ls.handlers;

import pt.isel.ls.data.IRatingData;
import pt.isel.ls.data.RatingData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.services.common.Handler;
import pt.isel.ls.services.common.HandlerException;
import pt.isel.ls.services.common.IHandler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;

/**
 * POST /movies/{mid}/ratings - submits a new rating for the movie identified
 * by mid, given the following parameters:
 *
 * rating - integer between 1 and 5.
 */
public class RateMovieHandler extends Handler implements IHandler {
    IRatingData ratingData;

    public RateMovieHandler() {
        super();
        ratingData = new RatingData();
        template.setParameters(new Parameters(new String[]{"rating"}));
    }

    public void setRatingDataConnection(IRatingData ratingData) {
        this.ratingData = ratingData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        if (!template.getParameters().isValid(cmd.getParameters())) {
            StringBuilder keys = new StringBuilder("Missing ");
            for (String str : template.getParameters()) {
                if (cmd.getParameters().getValue(str) == null) {
                    keys.append("\"").append(str).append("\" ");
                }
            }
            throw new HandlerException("Handler: missing parameters:\n"
                + keys.toString());
        }

        final int movie = Integer.parseInt(cmd.getPath().getValue(1));
        final int rate = Integer.parseInt(cmd.getParameters().getValue("rating"));

        try {
            return ratingData.createRating(movie, rate);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
