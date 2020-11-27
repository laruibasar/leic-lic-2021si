package pt.isel.ls.data;

import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.view.results.CommandResult;

public interface IMovieData {
    public CommandResult createMovie(String title, int year) throws DataConnectionException;

    public CommandResult getAllMovies() throws DataConnectionException;

    public CommandResult getMovie(int id) throws DataConnectionException;
}
