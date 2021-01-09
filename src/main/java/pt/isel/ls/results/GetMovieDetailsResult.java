package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.Review;
import pt.isel.ls.view.common.A;
import pt.isel.ls.view.common.Body;
import pt.isel.ls.view.common.Br;
import pt.isel.ls.view.common.Element;
import pt.isel.ls.view.common.Head;
import pt.isel.ls.view.common.Html;
import pt.isel.ls.view.common.Li;
import pt.isel.ls.view.common.Table;
import pt.isel.ls.view.common.Tbody;
import pt.isel.ls.view.common.Td;
import pt.isel.ls.view.common.Text;
import pt.isel.ls.view.common.Th;
import pt.isel.ls.view.common.Thead;
import pt.isel.ls.view.common.Title;
import pt.isel.ls.view.common.Tr;
import pt.isel.ls.view.common.Ul;

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

        ArrayList<Element> rows = new ArrayList<>();

        for (Review r: movie.getReviews()) {

            rows.add(
                    new Tr(
                            new Td(new A(r.getSummary(),"/movies/" + movie.getMid() + "/reviews/" + r.getId())),
                            new Td(String.valueOf(r.getRating())),
                            new Td(new A(r.getMovieCritic().getName(),"/users/" + r.getMovieCritic().getId()))
            ));
        }

        Html h = new Html(
                new Head(
                        new Title("Movie details")
                ),
                new Body(
                        new A("Return home","/"),
                        new Br(),
                        new A("See all movies","/movies"),
                        new Ul(new Li(new Text("Title: " + movie.getTitle())),
                                new Li(new Text("Year: " + movie.getYear())),
                                new Li(new A("Stars: " + movie.getRating(),"/movies/" + movie.getMid() + "/ratings"))
                        ),
                        new A(new Title("List of reviews"),"/movies/" + movie.getMid() + "/reviews"),
                        new Table(
                                new Thead(new Tr(new Th("Summary"),new Th("Rating"),new Th("Movie Critic"))),
                                new Tbody(rows)
                        )

                )
        );
        return h.print();
    }

    @Override
    public String printPlainText() {
        return movie == null ? "Movie details not available" : "Movie Details -> "
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
