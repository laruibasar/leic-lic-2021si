package pt.isel.ls.view.html;

import pt.isel.ls.model.MovieRating;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.Method;
import pt.isel.ls.view.common.IView;
import pt.isel.ls.view.common.elements.A;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Br;
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
import pt.isel.ls.view.common.elements.Th;
import pt.isel.ls.view.common.elements.Thead;
import pt.isel.ls.view.common.elements.Title;
import pt.isel.ls.view.common.elements.Tr;
import pt.isel.ls.view.common.elements.Ul;

public class GetMovieRatingsHtmlView extends HtmlView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        MovieRating movieRating = (MovieRating) cr.getResult();

        html = new Html(
                new Head(
                        new Title(movieRating.getMovieTitle() + " Ratings")
                ),
                new Body(
                        new A("Return home", "/"),
                        new Br(),
                        new Br(),
                        new Ul(
                                new Li(
                                        new A(movieRating.getMovieTitle(), "/movies/" + movieRating.getMovieId()),
                                        new Text(" average: " + String.valueOf(movieRating.getAverage()))
                                )
                        ),
                        new Table(
                                new Thead(
                                        new Tr(
                                                new Th("1star"),
                                                new Th("2stars"),
                                                new Th("3stars"),
                                                new Th("4stars"),
                                                new Th("5stars")
                                        )
                                ),
                                new Tbody(
                                        new Tr(
                                                new Td(String.valueOf(movieRating.getVotesOne())),
                                                new Td(String.valueOf(movieRating.getVotesTwo())),
                                                new Td(String.valueOf(movieRating.getVotesThree())),
                                                new Td(String.valueOf(movieRating.getVotesFour())),
                                                new Td(String.valueOf(movieRating.getVotesFive()))
                                        )
                                )
                        ),
                        new Br(),
                        new Text("<p>Add new rating</p>"),
                        new Form(
                                Method.POST,
                                "/movies/" + movieRating.getMovieId() + "/ratings",
                                new Label("1"),
                                new Input("radio", "rating", "value=\"1\" required"),
                                new Label("2"),
                                new Input("radio", "rating", "value=\"2\" required"),
                                new Label("3"),
                                new Input("radio", "rating", "value=\"3\" required"),
                                new Label("4"),
                                new Input("radio", "rating", "value=\"4\" required"),
                                new Label("5"),
                                new Input("radio", "rating", "value=\"5\" required"),
                                new Br(),
                                new Br(),
                                new Input("submit", "submit")
                        ),
                        new Br(),
                        new A("Previous page", "/movies/" + movieRating.getMovieId())
                )
        );

        return html.print();
    }
}
