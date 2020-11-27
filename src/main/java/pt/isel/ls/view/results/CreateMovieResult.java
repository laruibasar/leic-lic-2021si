package pt.isel.ls.view.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.User;
import pt.isel.ls.view.html.body.Body;
import pt.isel.ls.view.html.body.Table;
import pt.isel.ls.view.html.head.Head;
import pt.isel.ls.view.html.head.Title;
import pt.isel.ls.view.html.Html;

import java.util.ArrayList;
import java.util.List;

public class CreateMovieResult extends CommandResult{

    private Movie movie;

    public CreateMovieResult(List<Model> movies) {
        if(movies.size() != 1 || !(movies instanceof Movie)){
            //create exception
        }

        this.movie = (Movie) movies.get(0);
    }

    @Override
    public String printHTML() {
        ArrayList<String[]> rows = new ArrayList<>();
        rows.add(
                new String[] {
                        String.valueOf(movie.getMid()),
                        movie.getTitle(),
                        String.valueOf(movie.getYear())
                }
        );
        Html h = new Html(
                new Head(
                        new Title("Created Movie")
                ),
                new Body(
                        new Table(
                                rows
                        )
                )

        );
        return h.toString();
    }

    @Override
    public String printPlainText() {
        return "Created Movie -> " +
                movie.toString();
    }
}
