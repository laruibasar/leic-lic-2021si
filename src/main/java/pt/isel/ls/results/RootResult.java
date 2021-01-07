package pt.isel.ls.results;

import pt.isel.ls.view.common.A;
import pt.isel.ls.view.common.Body;
import pt.isel.ls.view.common.Head;
import pt.isel.ls.view.common.Html;
import pt.isel.ls.view.common.Li;
import pt.isel.ls.view.common.Title;
import pt.isel.ls.view.common.Ul;

public class RootResult extends CommandResult {
    @Override
    public String printHtml() {
        Html html = new Html(
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
                                        new A("Top movies", "/tops/ratings")
                                )
                        )
                )
        );

        return html.print();
    }

    @Override
    public String printPlainText() {
        return "Welcome to root page!";
    }
}
