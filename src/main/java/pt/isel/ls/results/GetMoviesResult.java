package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.view.common.A;
import pt.isel.ls.view.common.Body;
import pt.isel.ls.view.common.Element;
import pt.isel.ls.view.common.Head;
import pt.isel.ls.view.common.Html;
import pt.isel.ls.view.common.Table;
import pt.isel.ls.view.common.Tbody;
import pt.isel.ls.view.common.Td;
import pt.isel.ls.view.common.Text;
import pt.isel.ls.view.common.Th;
import pt.isel.ls.view.common.Thead;
import pt.isel.ls.view.common.Title;
import pt.isel.ls.view.common.Tr;
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
                            new Td(String.valueOf(movie.getMid())),
                            new Td(movie.getTitle()),
                            new Td(String.valueOf(movie.getYear())))
            );
        }

        //If size minor than 5 must not add on body
        A nextPage = movies.size() >= 5 ? new A("Next page ","https://localhost/movies?top=5&skip=") : new A("","");
        //Verify in cmd View the field skip
        A prevPage = new A("Previous page", "https://localhost/movies?top=5&skip=");

        Html h = new Html(
                new Head(
                        new Title("Movies List:")
                ),
                new Body(
                        new A("Return root","https://localhost/"),
                        new Text("&nbsp;"),
                        new Table(
                                new Thead(new Tr(new Th("Movie Id"),new Th("Title"),new Th("Year"))),
                                new Tbody(rows)
                        ),
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
            sb.append("MovieID = "
                    + movie.getMid()
                    + "\tTitle = "
                    + movie.getTitle());
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public boolean asResult() {
        return !movies.isEmpty();
    }
}
