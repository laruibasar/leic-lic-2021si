package pt.isel.ls.view.html;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.User;
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

public class GetAllUsersHtmlView extends HtmlView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        LinkedList<Model> users = (LinkedList<Model>) cr.getResult();
        ArrayList<Element> rows = new ArrayList<>();
        for (Model m: users) {
            User u = (User) m;
            rows.add(
                    new Tr(
                            new Td(new A(u.getName(), "/users/" + u.getId()))
                    )
            );
        }

        html = new Html(
                new Head(
                        new Title("Users List")
                ),
                new Body(
                        new A("Return home", "/"),
                        new Br(),
                        new Br(),
                        new Table(
                                new Thead(
                                        new Tr(
                                                new Th("Name")
                                        )
                                ),
                                new Tbody(
                                        rows
                                )
                        ),
                        new Br(),
                        new Text("Create new user"),
                        new Br(),
                        new Br(),
                        new Form(
                                Method.POST,
                                "",
                                new Label("Insert name"),
                                new Br(),
                                new Input("text", "name", "required"),
                                new Br(),
                                new Label("Insert email"),
                                new Br(),
                                new Input("email", "email", "required"),
                                new Br(),
                                new Br(),
                                new Input("submit", "submit")
                        )
                )
        );

        return html.print();
    }
}
