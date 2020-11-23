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
import pt.isel.ls.utils.Parameters;

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
        template.setParameters(
                new Parameters(new String[]{"title", "releaseYear"}));
    }

    // good for testing
    public void setMovieDataConnection(IMovieData movieData) {
        this.movieData = movieData;
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

        final String title = cmd
                .getParameters()
                .getValue("title")
                .replace("+", " ");

        final int year = Integer.parseInt(cmd.getParameters().getValue("releaseYear"));

        try {
            LinkedList<Model> result = ts.executeTransaction((connection) -> {
                return movieData.createMovie(connection, title, year);
            });

            return new CommandResult(result, result.size());
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
