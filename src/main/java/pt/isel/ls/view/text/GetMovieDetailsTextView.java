package pt.isel.ls.view.text;

import pt.isel.ls.model.Movie;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;

public class GetMovieDetailsTextView extends TextView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        Movie movie = (Movie) cr.getResult();

        if (movie == null) {
            return "Movie details not available";
        }

        StringBuilder sb = new StringBuilder("Movie Details:\n");
        sb.append("MovieID = ").append(movie.getMid()).append("\t");
        sb.append("Title = ").append(movie.getTitle()).append("\t");
        sb.append("Year= ").append(movie.getYear());

        return sb.toString();
    }
}
