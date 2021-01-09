package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.view.common.A;
import pt.isel.ls.view.common.Br;
import pt.isel.ls.view.common.Element;
import pt.isel.ls.view.common.Html;
import pt.isel.ls.view.common.Head;
import pt.isel.ls.view.common.Body;
import pt.isel.ls.view.common.Table;
import pt.isel.ls.view.common.Tbody;
import pt.isel.ls.view.common.Td;
import pt.isel.ls.view.common.Th;
import pt.isel.ls.view.common.Thead;
import pt.isel.ls.view.common.Title;
import pt.isel.ls.view.common.Tr;

import java.util.ArrayList;
import java.util.List;

public class GetTopRatingResult extends CommandResult {

    private final List<Model> movies;

    public GetTopRatingResult(List<Model> movies) {

        this.movies = movies;
    }

    @Override
    public String printHtml() {
        ArrayList<Element> rows = new ArrayList<>();
        for (Model m: movies) {
            Movie movie = (Movie) m;
            rows.add(
                    new Tr(
                            new Td(new A(String.valueOf(movie.getMid()), "/movies/" + movie.getMid())),
                            new Td(movie.getTitle()),
                            new Td(String.valueOf(movie.getYear()))
                    )
            );
        }

        Html html = new Html(
                new Head(
                        new Title("Top movies")
                ),
                new Body(
                        new A("Return home", "/"),
                        new Br(),
                        new Br(),
                        new Table(
                                new Thead(
                                        new Tr(
                                                new Th("Id"),
                                                new Th("Title"),
                                                new Th("Release Year")
                                        )
                                ),
                                new Tbody(rows)
                        ),
                        new Br(),
                        new A("List all movies", "/movies")
                )
        );

        return html.print();
    }

    @Override
    public String printPlainText() {
        StringBuilder sb = new StringBuilder("Top Ratings: \n");
        for (Model m : movies) {
            Movie movie = (Movie) m;
            sb.append("MovieID = ").append(movie.getMid());
            sb.append("\tTitle = ").append(movie.getTitle());
            sb.append("\tYear = ").append(movie.getYear());
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public boolean asResult() {
        return !movies.isEmpty();
    }
}
