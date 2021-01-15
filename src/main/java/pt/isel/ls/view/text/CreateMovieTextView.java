package pt.isel.ls.view.text;

import pt.isel.ls.model.Movie;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;

public class CreateMovieTextView extends TextView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        Movie movie = (Movie) cr.getResult();
        return movie == null ? "Movie not created" : "Created Movie:\n "
                + "MovieID = " + movie.getMid()
                + "\tTitle = " + movie.getTitle()
                + "\tYear = " + movie.getYear();
    }
}
