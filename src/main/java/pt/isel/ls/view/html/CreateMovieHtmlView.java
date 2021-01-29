package pt.isel.ls.view.html;

import pt.isel.ls.model.Movie;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Br;
import pt.isel.ls.view.common.elements.Head;
import pt.isel.ls.view.common.elements.Html;
import pt.isel.ls.view.common.elements.Li;
import pt.isel.ls.view.common.elements.Text;
import pt.isel.ls.view.common.elements.Title;
import pt.isel.ls.view.common.elements.Ul;

public class CreateMovieHtmlView extends HtmlView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        Movie movie = (Movie) cr.getResult();
        html = new Html(
                new Head(
                        new Title("Created Movie")
                ),
                new Body(
                        new Br(),
                        new Br(),
                        new Ul(new Li(new Text("Title: " + movie.getTitle())),
                                new Li(new Text("Year: " + movie.getYear()))
                        ),
                        new Br()
                )
        );
        return html.print();
    }
}
