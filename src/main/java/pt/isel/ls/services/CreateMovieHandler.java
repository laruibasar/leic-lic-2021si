package pt.isel.ls.services;

import pt.isel.ls.data.IMovieData;
import pt.isel.ls.data.MovieData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;

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
            return movieData.createMovie(title, year);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
