package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.view.common.elements.A;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Br;
import pt.isel.ls.view.common.elements.Element;
import pt.isel.ls.view.common.elements.Head;
import pt.isel.ls.view.common.elements.Html;
import pt.isel.ls.view.common.elements.Table;
import pt.isel.ls.view.common.elements.Tbody;
import pt.isel.ls.view.common.elements.Td;
import pt.isel.ls.view.common.elements.Th;
import pt.isel.ls.view.common.elements.Thead;
import pt.isel.ls.view.common.elements.Title;
import pt.isel.ls.view.common.elements.Tr;
import java.util.ArrayList;
import java.util.List;

public class GetMoviesResult extends CommandResult {
    private final List<Model> movies;

    public GetMoviesResult(List<Model> movies) {
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
                                    new A(movie.getTitle(), "/movies/" + String.valueOf(movie.getMid())),
                            new Td(String.valueOf(movie.getYear()))
                            )
                    )
            );
        }

        //If size minor than 5 must not add on body
        A nextPage = movies.size() >= 5 ? new A("Next page ","/movies?top=5&skip=") : new A("","");
        //Verify in cmd View the field skip
        A prevPage = new A("Previous page", "/movies?top=5&skip=");

        Html h = new Html(
                new Head(
                        new Title("Movies List:")
                ),
                new Body(
                        new A("Return home","/"),
                        new Br(),
                        new Br(),
                        new Table(
                                new Thead(
                                        new Tr(
                                                new Th("Title"),
                                                new Th("Year")
                                        )
                                ),
                                new Tbody(rows)
                        ),
                        new Br(),
                        new Br(),
                        prevPage,
                        nextPage
                )
        );
        return h.print();
    }

    @Override
    public String printPlainText() {
        StringBuilder sb = new StringBuilder("Movies list: \n");
        for (Model m : movies) {
            Movie movie = (Movie) m;
            sb.append("MovieID = ").append(movie.getMid());
            sb.append("\tTitle = ").append(movie.getTitle());
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public boolean asResult() {
        return !movies.isEmpty();
    }
}
