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
import pt.isel.ls.results.GetMovieDetailsResult;

import java.util.LinkedList;

/**
 * GET /movies/{mid} - returns the detailed information for the movie identified by mid
 */
public class GetMovieDetailsHandler extends Handler implements IHandler {
    IMovieData movieData;

    public GetMovieDetailsHandler() {
        super();
        movieData = new MovieData();
        description = "Return the detailed information for the movie identified by mid";

        validValues.add("mid");
    }

    public void setMovieDataConnection(IMovieData movieData) {
        this.movieData = movieData;
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

        try {
            LinkedList<Model> result = ts.executeTransaction((connection) -> {
                return movieData.getMovie(connection, movie);
            });

            return new GetMovieDetailsResult(result);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
