package pt.isel.ls.view.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.view.html.Html;
import pt.isel.ls.view.html.body.Body;
import pt.isel.ls.view.html.body.Table;
import pt.isel.ls.view.html.head.Head;
import pt.isel.ls.view.html.head.Title;

import java.util.ArrayList;
import java.util.List;

public class GetTopRatingResult extends CommandResult {

    private final List<Model> movies;

    public GetTopRatingResult(List<Model> movies) {

        this.movies = movies;
    }

    @Override
    public String printHtml() {
        ArrayList<String> header = new ArrayList<>();
        header.add("Movie Id");
        header.add("Title");
        header.add("Year");

        ArrayList<String[]> rows = new ArrayList<>();
        for (Model m: movies) {
            Movie movie = (Movie) m;
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
                        new Title("Top Ratings")
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
        StringBuilder sb = new StringBuilder("Top Ratings -> \n");
        for (Model m : movies) {
            sb.append(m.toString());
            sb.append('\n');
        }
        return sb.toString();
    }
}
