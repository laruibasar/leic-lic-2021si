package pt.isel.ls.data;

import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.utils.CommandResult;

public interface ITopRatingData {
    public CommandResult getTopRating(int number, int average, int min)
            throws DataConnectionException;
}
