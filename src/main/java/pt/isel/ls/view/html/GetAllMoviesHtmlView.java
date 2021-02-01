package pt.isel.ls.view.html;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.Method;
import pt.isel.ls.view.common.IView;
import pt.isel.ls.view.common.elements.A;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Br;
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
import pt.isel.ls.view.common.elements.Th;
import pt.isel.ls.view.common.elements.Thead;
import pt.isel.ls.view.common.elements.Title;
import pt.isel.ls.view.common.elements.Tr;

import java.util.ArrayList;
import java.util.LinkedList;

public class GetAllMoviesHtmlView extends HtmlView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        LinkedList<Model> movies = (LinkedList<Model>) cr.getResult();
        ArrayList<Element> rows = new ArrayList<>();
        System.out.println("GET ALL MOVIES");
        System.out.println(cmd.getParameters().getParameters());

        int count = 0;
        for (Model m : movies) {
            if (++count == 5) {
                break;
            }
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
        A nextPage = movies.size() >= 5
                ? new A("Next page ","/movies?top=10&skip=5")
                : new A("","");

        //Verify in cmd View the field skip
        A prevPage = new A("Previous page", "/movies?top=5&skip=0");

        html = new Html(
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
                        nextPage,
                        new Br(),
                        new Br(),
                        new Text("<p>Add new Movie</p>"),
                        new Form(
                                Method.POST,
                                "/movies",
                                new Label("Title"),
                                new Input("text", "title", "required"),
                                new Br(),
                                new Label("Release Year"),
                                new Input("number", "releaseYear", "required"),
                                new Br(),
                                new Br(),
                                new Input("submit", "submit")
                        )
                )
        );

        return html.print();
    }
}
