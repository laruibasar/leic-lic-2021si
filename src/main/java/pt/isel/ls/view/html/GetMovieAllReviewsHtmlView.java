package pt.isel.ls.view.html;

import pt.isel.ls.model.Movie;
import pt.isel.ls.model.Review;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.Method;
import pt.isel.ls.view.common.IView;
import pt.isel.ls.view.common.elements.A;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Br;
import pt.isel.ls.view.common.elements.Div;
import pt.isel.ls.view.common.elements.Element;
import pt.isel.ls.view.common.elements.Form;
import pt.isel.ls.view.common.elements.Head;
import pt.isel.ls.view.common.elements.Html;
import pt.isel.ls.view.common.elements.Input;
import pt.isel.ls.view.common.elements.Label;
import pt.isel.ls.view.common.elements.Table;
import pt.isel.ls.view.common.elements.Tbody;
import pt.isel.ls.view.common.elements.Td;
import pt.isel.ls.view.common.elements.Text;
import pt.isel.ls.view.common.elements.Textarea;
import pt.isel.ls.view.common.elements.Th;
import pt.isel.ls.view.common.elements.Thead;
import pt.isel.ls.view.common.elements.Title;
import pt.isel.ls.view.common.elements.Tr;

import java.util.ArrayList;
import java.util.LinkedList;

public class GetMovieAllReviewsHtmlView extends HtmlView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        LinkedList<Review> reviews = (LinkedList<Review>) cr.getResult();

        ArrayList<Element> rows = new ArrayList<>();
        Review review;
        Movie movie = ((Review) reviews.get(0)).getMovie();
        for (Review r: reviews) {
            review = r;
            rows.add(
                    new Tr(
                            new Td(
                                    new A(
                                            review.getSummary(),
                                            "/movies/" + movie.getMid() + "/reviews/" + review.getId())),
                            new Td(String.valueOf(review.getRating())),
                            new Td(review.getMovieCritic().getName())
                    ));
        }

        html = new Html(
                new Head(
                        new Title("Movie details")
                ),
                new Body(
                        new A("Return home","/"),
                        new Br(),
                        new Br(),
                        new Text("List of reviews of movie "),
                        new A(movie.getTitle(),"/movies/" + movie.getMid()),
                        new Br(),
                        new Table(
                                new Thead(
                                        new Tr(
                                                new Th("Summary"),
                                                new Th("Rating"),
                                                new Th("Movie Critic")
                                        )
                                ),
                                new Tbody(rows)
                        )
                )
        );

        return html.print();
    }
}
