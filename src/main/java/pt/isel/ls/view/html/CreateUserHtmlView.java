package pt.isel.ls.view.html;

import pt.isel.ls.model.User;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Head;
import pt.isel.ls.view.common.elements.Html;
import pt.isel.ls.view.common.elements.Li;
import pt.isel.ls.view.common.elements.Text;
import pt.isel.ls.view.common.elements.Title;
import pt.isel.ls.view.common.elements.Ul;

public class CreateUserHtmlView extends HtmlView implements IView {

    @Override
    public String print(Command cmd, CommandResult cr) {
        User user = (User) cr.getResult();
        html = new Html(
                new Head(
                        new Title("Created user")
                ),
                new Body(
                        new Ul(
                                new Li(new Text("User id: " + user.getId())),
                                new Li(new Text("Name: " + user.getName())),
                                new Li(new Text("Email: " + user.getEmail()))
                        )

                )
        );
        return html.print();
    }
}
