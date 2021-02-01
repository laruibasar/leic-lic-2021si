package pt.isel.ls.view.html;

import pt.isel.ls.model.Rating;
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

public class RateMovieHtmlView extends HtmlView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        Rating rating = (Rating) cr.getResult();
        html = new Html(
                new Head(
                        new Title("Rating added")
                ),
                new Body(
                        new Br(),
                        new Br(),
                        new Ul(new Li(new Text("Id: " + rating.getRatingId())),
                                new Li(new Text("MovieId: " + rating.getMovieId())),
                                new Li(new Text("Rating: " + rating.getRating()))
                        ),
                        new Br()
                )
        );
        return html.print();
    }
}
