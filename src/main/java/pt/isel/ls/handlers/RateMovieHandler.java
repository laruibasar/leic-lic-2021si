package pt.isel.ls.handlers;

import pt.isel.ls.data.IRatingData;
import pt.isel.ls.data.RatingData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.model.Model;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.util.LinkedList;

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
        description = "Submit a new rating for the movie identified by mid";

        validValues.add("mid");
        validValues.add("rating");
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

        int rate;
        try {
            rate = Integer.parseInt(cmd.getValue("rating"));
        } catch (NumberFormatException e) {
            throw new HandlerException("Handler invalid format for rating: "
                    + cmd.getValue("rating"));
        }

        try {
            LinkedList<Model> result = ts.executeTransaction((connection) -> {
                return ratingData.createRating(connection, movie, rate);
            });

            return new CommandResult(result, result.size());
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
