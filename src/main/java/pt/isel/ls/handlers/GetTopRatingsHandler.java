package pt.isel.ls.handlers;

import pt.isel.ls.data.ITopRatingData;
import pt.isel.ls.data.TopRatingData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.model.Model;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.util.LinkedList;

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
    ITopRatingData topData;

    public GetTopRatingsHandler() {
        super();
        topData = new TopRatingData();
        description = "Return a list with top movies by ratings";

        validValues.add("n");
        validValues.add("average");
        validValues.add("min");
    }

    public void setTopDataConnection(ITopRatingData topData) {
        this.topData = topData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        String check = checkNeededValues(cmd);
        if (check.length() > 0) {
            throw new HandlerException("Handler missing parameters: " + check);
        }

        int number;
        try {
            number = Integer.parseInt(cmd.getValue("n"));
        } catch (NumberFormatException e) {
            throw new HandlerException("Handler invalid format for number: "
                    + cmd.getValue("n"));
        }

        final String avg = cmd.getValue("average");
        int average;
        switch (avg) {
            case "highest":
                average = 1;
                break;
            case "lowest":
                average = 0;
                break;
            default:
                throw new HandlerException("Handler: parameter average only allow: "
                        + "highest or lowest, sent: " + avg);
        }

        int min;
        try {
            min = Integer.parseInt(cmd.getValue("min"));
        } catch (NumberFormatException e) {
            throw new HandlerException("Handler invalid format for number: "
                    + cmd.getValue("min"));
        }

        try {
            LinkedList<Model> result = ts.executeTransaction((connection) -> {
                return topData.getTopRating(connection, number, average, min);
            });

            return new CommandResult(result, result.size());
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
