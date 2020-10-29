package pt.isel.ls.services;

import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.SQLException;

//vai buscar determinada (reviewId) review ao filme
public class GetReviewFromMovieHandler extends Handler implements IHandler {

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        return null;
    }
}
