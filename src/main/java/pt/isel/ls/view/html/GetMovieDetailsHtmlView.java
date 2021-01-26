package pt.isel.ls.view.html;

import pt.isel.ls.model.Movie;
import pt.isel.ls.model.Review;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;
import pt.isel.ls.view.common.elements.A;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Br;
import pt.isel.ls.view.common.elements.Element;
import pt.isel.ls.view.common.elements.Head;
import pt.isel.ls.view.common.elements.Html;
import pt.isel.ls.view.common.elements.Li;
import pt.isel.ls.view.common.elements.Table;
import pt.isel.ls.view.common.elements.Tbody;
import pt.isel.ls.view.common.elements.Td;
import pt.isel.ls.view.common.elements.Text;
import pt.isel.ls.view.common.elements.Th;
import pt.isel.ls.view.common.elements.Thead;
import pt.isel.ls.view.common.elements.Title;
import pt.isel.ls.view.common.elements.Tr;
import pt.isel.ls.view.common.elements.Ul;

import java.util.ArrayList;

public class GetMovieDetailsHtmlView extends HtmlView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        Movie movie = (Movie) cr.getResult();

        ArrayList<Element> rows = new ArrayList<>();
        for (Review r: movie.getReviews()) {
            rows.add(
                    new Tr(
                            new Td(new A(r.getSummary(),"/movies/" + movie.getMid() + "/reviews/" + r.getId())),
                            new Td(String.valueOf(r.getRating())),
                            new Td(new A(r.getMovieCritic().getName(),"/users/" + r.getMovieCritic().getId()))
                    )
            );
        }

        html = new Html(
                new Head(
                        new Title("Movie details")
                ),
                new Body(
                        new A("Return home","/"),
                        new Br(),
                        new Br(),
                        new A("Return to all movies","/movies"),
                        new Ul(new Li(new Text("Title: " + movie.getTitle())),
                                new Li(new Text("Year: " + movie.getYear())),
                                new Li(new A("Stars: " + movie.getRating(),"/movies/" + movie.getMid() + "/ratings"))
                        ),
                        new A(new Title("List of reviews"),"/movies/" + movie.getMid() + "/reviews"),
                        new Table(
                                new Thead(new Tr(new Th("Summary"),new Th("Rating"),new Th("Movie Critic"))),
                                new Tbody(rows)
                        ),
                        new Br(),
                        new A("All reviews", "/movies/" + movie.getMid() + "/reviews")
                )
        );

        return html.print();
    }
}
