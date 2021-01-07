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
import pt.isel.ls.results.CreateMovieResult;

import java.util.LinkedList;

/**
 * POST /movies - creates a new movie, given the following parameters
 *  title - the movie's name.
 *  releaseYear - the movie's release year.
 */
public class CreateMovieHandler extends Handler implements IHandler {
    IMovieData movieData;

    public CreateMovieHandler() {
        super();
        movieData = new MovieData();
        description = "Create a new movie";

        validValues.add("title");
        validValues.add("releaseYear");
    }

    // good for testing
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

        String title = cmd.getValue("title");

        int year;
        try {
            year = Integer.parseInt(cmd.getValue("releaseYear"));
        } catch (NumberFormatException e) {
            throw new HandlerException("Handler invalid format for releaseYear "
                + cmd.getValue("releaseYear"));
        }

        try {
            LinkedList<Model> result = ts.executeTransaction((connection) -> {
                return movieData.createMovie(connection, title, year);
            });

            return new CreateMovieResult(result);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
