package pt.isel.ls.view.html;

import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;
import pt.isel.ls.view.common.elements.A;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Br;
import pt.isel.ls.view.common.elements.Head;
import pt.isel.ls.view.common.elements.Html;
import pt.isel.ls.view.common.elements.Text;
import pt.isel.ls.view.common.elements.Title;

public class GetRedirectErrorHtmlView extends HtmlView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        int statusCode = Integer.parseInt(cmd.getValue("error"));
        int statusCode = Integer.parseInt(cmd.getValue("resp"));
        Text text = null;
        switch (statusCode) {
            case 404:
                text = new Text("Resource not found ------");
                break;
            case 500:
                text = new Text("Internal error");
                break;
            default:
                break;
        }

        Html html = new Html(
                new Head(
                        new Title("Redirection failed")
                ),
                new Body(
                        new A("Return home", "/"),
                        new Br(),
                        new Br(),
                        text
                )
        );

        return html.print();
    }
}
