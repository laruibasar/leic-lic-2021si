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
import pt.isel.ls.view.common.elements.Li;
import pt.isel.ls.view.common.elements.Table;
import pt.isel.ls.view.common.elements.Tbody;
import pt.isel.ls.view.common.elements.Td;
import pt.isel.ls.view.common.elements.Text;
import pt.isel.ls.view.common.elements.Textarea;
import pt.isel.ls.view.common.elements.Th;
import pt.isel.ls.view.common.elements.Thead;
import pt.isel.ls.view.common.elements.Title;
import pt.isel.ls.view.common.elements.Tr;
import pt.isel.ls.view.common.elements.Ul;

import java.util.ArrayList;
import java.util.LinkedList;

public class GetMovieDetailsHtmlView extends HtmlView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        Movie movie = (Movie) cr.getResult();

        ArrayList<Element> rows = new ArrayList<>();
        LinkedList<Review> reviews = movie.getReviews();
        for (Review r: reviews) {
            rows.add(
                    new Tr(
                            new Td(new A(r.getSummary(),"/movies/" + movie.getMid() + "/reviews/" + r.getId())),
                            new Td(String.valueOf(r.getRating())),
                            new Td(new A(r.getMovieCritic().getName(),"/users/" + r.getMovieCritic().getId()))
                    )
            );
        }

        A allReviews = reviews.size() != 0
                ? new A("All reviews", "/movies/" + movie.getMid() + "/reviews")
                : new A("","");

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
                        allReviews,
                        new Br(),
                        new Text("<p>Add new review</p>"),
                        new Form(
                                Method.POST,
                                "/movies/" + movie.getMid() + "/reviews",
                                new Div(new Label("Reviewer:"),
                                        new Input("number","uid")),
                                new Div(new Label("Title of Review"),
                                        new Input("text","reviewSummary")),
                                new Label("Rate this movie:"),
                                new Div(new Label("1"),
                                        new Input("radio", "rating", "value=\"1\" required"),
                                        new Label("2"),
                                        new Input("radio", "rating", "value=\"2\" required"),
                                        new Label("3"),
                                        new Input("radio", "rating", "value=\"3\" required"),
                                        new Label("4"),
                                        new Input("radio", "rating", "value=\"4\" required"),
                                        new Label("5"),
                                        new Input("radio", "rating", "value=\"5\" required")),
                                new Div(new Label("Write your review"),
                                        new Textarea("review")),
                                new Br(),
                                new Input("submit", "submit")
                        )
                )
        );

        return html.print();
    }
}
