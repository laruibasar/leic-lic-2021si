package pt.isel.ls.view.text;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;

import java.util.LinkedList;

public class GetTopsRatingsTextView extends TextView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        LinkedList<Movie> movies = (LinkedList<Movie>) cr.getResult();

        StringBuilder sb = new StringBuilder("Top Ratings: \n");
        for (Model m : movies) {
            Movie movie = (Movie) m;
            sb.append("MovieID = ").append(movie.getMid()).append("\t");
            sb.append("Title = ").append(movie.getTitle()).append("\t");
            sb.append("Year = ").append(movie.getYear()).append("\n");
        }

        return sb.toString();
    }
}
