package pt.isel.ls.handlers;

import pt.isel.ls.data.IMovieData;
import pt.isel.ls.data.MovieData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.results.GetMoviesResult;

import java.util.LinkedList;

/**
 * GET /movies - returns a list with all movies.
 */
public class GetMoviesHandler extends Handler implements IHandler {
    IMovieData movieData;

    public GetMoviesHandler() {
        super();
        movieData = new MovieData();
        description = "Return list with all movies";

        validValues.add("top");
        validValues.add("skip");
    }

    public void setMovieDataConnection(IMovieData movieData) {
        this.movieData = movieData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        int top = 0; /* change to 0 for allow ALL */
        int skip = 0;
        try {
            if (cmd.getValue("top") != null) {
                top = Integer.parseInt(cmd.getValue("top"));
                if (top < 0) {
                    throw new NumberFormatException("Top cannot be negative: " + top);
                }
            }

            if (cmd.getValue("skip") != null) {
                skip = Integer.parseInt(cmd.getValue("skip")) + 1;
                if (skip < 0) {
                    throw new NumberFormatException("Skip cannot be negative: " + skip);
                }
            }
        } catch (NumberFormatException e) {
            throw new HandlerException("Handler invalid format: "
                    + e.getMessage());
        }

        try {
            int finalTop = top;
            int finalSkip = skip;
            LinkedList<Model> result = ts.executeTransaction((connection) -> {
                return movieData.getAllMovies(connection, finalTop, finalSkip);
            });

            return new GetMoviesResult(result);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
