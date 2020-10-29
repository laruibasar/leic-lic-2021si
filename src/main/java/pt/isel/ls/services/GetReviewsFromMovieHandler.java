package pt.isel.ls.services;

import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.SQLException;


//O texto é apenas o excerto da review, e não todas as reviews completas
public class GetReviewsFromMovieHandler extends Handler implements IHandler {

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        return null;
    }
}
