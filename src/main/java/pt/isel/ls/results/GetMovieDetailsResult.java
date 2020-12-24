package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.view.html.Html;
import pt.isel.ls.view.html.body.Body;
import pt.isel.ls.view.html.body.Table;
import pt.isel.ls.view.html.head.Head;
import pt.isel.ls.view.html.head.Title;

import java.util.ArrayList;
import java.util.List;

public class GetMovieDetailsResult extends CommandResult {

    private Movie movie;

    public GetMovieDetailsResult(List<Model> movies) {
        if (movies.size() != 0) {
            this.movie = (Movie) movies.get(0);
        }
    }

    @Override
    public String printHtml() {
        ArrayList<String> header = new ArrayList<>();
        header.add("Id");
        header.add("Title");
        header.add("Year");
        ArrayList<String[]> rows = new ArrayList<>();
        if (movie == null) {
            rows.add(new String[]{ "Movie details not available" });
        } else {
            rows.add(new String[] {
                    String.valueOf(movie.getMid()),
                    movie.getTitle(),
                    String.valueOf(movie.getYear())});
        }

        Html h = new Html(
                new Head(
                        new Title("Movie details")
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

    @Override
    public String printPlainText() {
        return movie == null ? "Movie details not available" : "Movie Details -> " + movie.toString();
    }
}
