package pt.isel.ls.view.html;

import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Head;
import pt.isel.ls.view.common.elements.Html;
import pt.isel.ls.view.common.elements.Text;
import pt.isel.ls.view.common.elements.Title;

public class ExitHtmlView extends HtmlView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        html = new Html(
                new Head(
                   new Title("Exit")
                ),
                new Body(
                    new Text("Exit program")
                )
        );

        return html.print();
    }
}
