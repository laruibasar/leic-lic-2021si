package pt.isel.ls.view.html;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;
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
import java.util.LinkedList;

public class GetTopsRatingsHtmlView extends HtmlView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        LinkedList<Movie> movies = (LinkedList<Movie>) cr.getResult();

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
}
