package pt.isel.ls.handlers;

import pt.isel.ls.data.IRatingData;
import pt.isel.ls.data.RatingData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
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
        description = "Return the rating information for the movie identified by mid";

        validValues.add("mid");
    }

    public void setRatingDataConnection(IRatingData ratingData) {
        this.ratingData = ratingData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        String check = checkNeededValues(cmd);
        if (check.length() > 0) {
            throw new HandlerException("Handler missing parameters: "
                    + check);
        }

        int movie;
        try {
            movie = Integer.parseInt(cmd.getValue("mid"));
        } catch (NumberFormatException e) {
            throw new HandlerException("Handler invalid format for mid: "
                    + cmd.getValue("mid"));
        }

        try {
            return ratingData.getRatings(movie);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}


