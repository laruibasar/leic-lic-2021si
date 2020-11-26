package pt.isel.ls.handlers;

import pt.isel.ls.data.IMovieData;
import pt.isel.ls.data.MovieData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

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
        try {
            return movieData.getMovie(Integer.parseInt(cmd.getPath().getValue(1)));
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
