package pt.isel.ls.view.html;

import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;
import pt.isel.ls.view.common.elements.A;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Head;
import pt.isel.ls.view.common.elements.Html;
import pt.isel.ls.view.common.elements.Li;
import pt.isel.ls.view.common.elements.Title;
import pt.isel.ls.view.common.elements.Ul;

public class RootHtmlView extends HtmlView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        html = new Html(
                new Head(
                        new Title("Application LS - G8")
                ),
                new Body(
                        new Ul(
                                new Li(
                                        new A("List users", "/users")
                                ),
                                new Li(
                                        new A("List movies", "/movies")
                                ),
                                new Li(
                                        new A("Top movies", "/tops/ratings&n=5&average=highest&min=1")
                                )
                        )
                )
        );

        return html.print();
    }
}
