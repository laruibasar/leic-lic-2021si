package pt.isel.ls.view.html;

import pt.isel.ls.config.Router;
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

import java.util.ArrayList;

public class OptionHtmlView extends HtmlView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        Router router = (Router) cr.getResult();

        ArrayList<Li> lis = new ArrayList<>();
        for (Command c : router) {
            lis.add(new Li(new Text(c.toString()), new Br(), new Text(router.getHandler(c).getDescription())));
        }

        html = new Html(
                new Head(
                        new Title("All commands")
                ),
                new Body(
                        new Ul(
                                lis
                        )
                )
        );

        return html.print();
    }
}
