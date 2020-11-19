package pt.isel.ls.services;

import pt.isel.ls.data.ITopRatingData;
import pt.isel.ls.data.TopRatingData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.services.common.Handler;
import pt.isel.ls.services.common.HandlerException;
import pt.isel.ls.services.common.IHandler;
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
    ITopRatingData topData;

    public GetTopRatingsHandler() {
        super();
        topData = new TopRatingData();
        template.setParameters(new Parameters(new String[]{"n", "average", "min"}));
    }

    public void setTopDataConnection(ITopRatingData topData) {
        this.topData = topData;
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
            throw new HandlerException("Handler: missing parameters: "
                    + keys.toString());
        }

        final int number = Integer.parseInt(cmd.getParameters().getValue("n"));
        final int min = Integer.parseInt(cmd.getParameters().getValue("min"));

        final String avg = cmd.getParameters().getValue("average");
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

        try {
            return topData.getTopRating(number, average, min);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
