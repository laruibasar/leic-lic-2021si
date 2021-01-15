package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.view.common.elements.A;
import pt.isel.ls.view.common.elements.Br;
import pt.isel.ls.view.common.elements.Element;
import pt.isel.ls.view.common.elements.Html;
import pt.isel.ls.view.common.elements.Head;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Table;
import pt.isel.ls.view.common.elements.Tbody;
import pt.isel.ls.view.common.elements.Td;
import pt.isel.ls.view.common.elements.Th;
import pt.isel.ls.view.common.elements.Thead;
import pt.isel.ls.view.common.elements.Title;
import pt.isel.ls.view.common.elements.Tr;

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
                            new Td(
                                    new A(movie.getTitle(), "/movies/" + movie.getMid())
                            ),
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

    @Override
    public Object getResult() {
        return movies;
    }
}
