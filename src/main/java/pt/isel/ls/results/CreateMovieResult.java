package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.view.htmlold.Html;
import pt.isel.ls.view.htmlold.body.Body;
import pt.isel.ls.view.htmlold.body.Table;
import pt.isel.ls.view.htmlold.head.Head;
import pt.isel.ls.view.htmlold.head.Title;

import java.util.ArrayList;
import java.util.List;

public class CreateMovieResult extends CommandResult {

    private Movie movie;

    public CreateMovieResult() {
    }

    public CreateMovieResult(List<Model> movies) {
        if (movies.size() != 0) {
            this.movie = (Movie) movies.get(0);
        }
    }

    @Override
    public Object getResult() {
        return movie;
    }

    @Override
    public int getResultId() {
        return (movie != null) ? movie.getMid() : 0;
    }

    public String printHtml() {
        ArrayList<String> header = new ArrayList<>();
        header.add("Movie Id");
        header.add("Title");
        header.add("Year");

        ArrayList<String[]> rows = new ArrayList<>();
        String title = "Created Movie";

        if (movie == null) {
            title = "Movie not created";
        } else {
            rows.add(
                    new String[] {
                            String.valueOf(movie.getMid()),
                            movie.getTitle(),
                            String.valueOf(movie.getYear())
                    }
            );
        }

        Html h = new Html(
                new Head(
                        new Title(title)
                ),
                new Body(
                        new Table(
                                header,
                                rows
                        )
                )

        );
        return h.toString();
    }

    public String printPlainText() {
        return movie == null ? "Movie not created" : "Created Movie -> "
                + "MovieID = "
                + movie.getMid()
                + "\tTitle = "
                + movie.getTitle()
                + "\tYear = "
                + movie.getYear();
    }

    @Override
    public boolean asResult() {
        return movie != null;
    }
}
