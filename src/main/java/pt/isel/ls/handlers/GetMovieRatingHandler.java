package pt.isel.ls.handlers;

import pt.isel.ls.data.IRatingData;
import pt.isel.ls.data.RatingData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.services.common.Handler;
import pt.isel.ls.services.common.HandlerException;
import pt.isel.ls.services.common.IHandler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

/**
 * GET /movies/{mid}/ratings - returns the rating information for the movie
 * identified by mid. This include:
 * The rating average
 * The number of votes for each rating value
 */
public class GetMovieRatingHandler extends Handler implements IHandler {
    IRatingData ratingData;

    public GetMovieRatingHandler() {
        super();
        ratingData = new RatingData();
    }

    public void setRatingDataConnection(IRatingData ratingData) {
        this.ratingData = ratingData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        final int movie = Integer.parseInt(cmd.getPath().getValue(1));

        try {
            return ratingData.getRatings(movie);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}


