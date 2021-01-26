package pt.isel.ls.results;

import pt.isel.ls.view.common.elements.A;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Head;
import pt.isel.ls.view.common.elements.Html;
import pt.isel.ls.view.common.elements.Li;
import pt.isel.ls.view.common.elements.Title;
import pt.isel.ls.view.common.elements.Ul;

public class RootResult extends CommandResult {
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
                                        new A("Top movies", "/tops/ratings&n=5&average=highest&min=1")
                                )
                        )
                )
        );

        return html.print();
    }

    public String printPlainText() {
        return "Welcome to root page!";
    }

    @Override
    public boolean asResult() {
        return true;
    }

    @Override
    public Object getResult() {
        return null;
    }

    @Override
    public int getResultId() {
        return 0;
    }
}
