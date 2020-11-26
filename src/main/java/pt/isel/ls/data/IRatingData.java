package pt.isel.ls.data;

import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.utils.CommandResult;

public interface IRatingData {
    public CommandResult createRating(int movie, int rate) throws DataConnectionException;

    public CommandResult getRatings(int movie) throws DataConnectionException;
}
