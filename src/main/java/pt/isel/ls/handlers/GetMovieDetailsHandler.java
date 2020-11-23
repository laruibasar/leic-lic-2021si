package pt.isel.ls.handlers;

import pt.isel.ls.data.IMovieData;
import pt.isel.ls.data.MovieData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.data.transaction.DataTransaction;
import pt.isel.ls.data.transaction.IDataTransaction;
import pt.isel.ls.model.Model;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.util.LinkedList;

/**
 * GET /movies/{mid} - returns the detailed information for the movie identified by mid
 */
public class GetMovieDetailsHandler extends Handler implements IHandler {
    IMovieData movieData;

    public GetMovieDetailsHandler() {
        super();
        movieData = new MovieData();
    }

    public void setMovieDataConnection(IMovieData movieData) {
        this.movieData = movieData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        IDataTransaction ts = new DataTransaction();
        try {
            final int mid = Integer.parseInt(cmd.getPath().getValue(1));
        } catch (NumberFormatException e) {
            throw new HandlerException("Handler: movie ID invalid " + cmd.getPath().getValue(1)
                + "\n" + e.getMessage());
        }

        try {
            LinkedList<Model> result = ts.executeTransaction((connection) -> {
                return movieData.getMovie(connection, Integer.parseInt(cmd.getPath().getValue(1)));
            });

            return new CommandResult(result, result.size());
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
