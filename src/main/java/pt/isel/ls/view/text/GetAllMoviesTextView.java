package pt.isel.ls.view.text;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;

import java.util.LinkedList;

public class GetAllMoviesTextView extends TextView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        LinkedList<Model> movies = (LinkedList<Model>) cr.getResult();

        StringBuilder sb = new StringBuilder("Movies list: \n");
        for (Model m : movies) {
            Movie movie = (Movie) m;
            sb.append("MovieID = ").append(movie.getMid());
            sb.append("\tTitle = ").append(movie.getTitle());
            sb.append('\n');
        }
        return sb.toString();
    }
}
