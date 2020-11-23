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
 * GET /movies - returns a list with all movies.
 */
public class GetMoviesHandler extends Handler implements IHandler {
    IMovieData movieData;

    public GetMoviesHandler() {
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
            LinkedList<Model> result = ts.executeTransaction((connection) -> {
                return movieData.getAllMovies(connection);
            });

            return new CommandResult(result, result.size());
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
