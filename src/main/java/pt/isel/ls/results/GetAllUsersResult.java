package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.User;
import pt.isel.ls.view.common.elements.A;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Br;
import pt.isel.ls.view.common.elements.Element;
import pt.isel.ls.view.common.elements.Head;
import pt.isel.ls.view.common.elements.Html;
import pt.isel.ls.view.common.elements.Table;
import pt.isel.ls.view.common.elements.Tbody;
import pt.isel.ls.view.common.elements.Td;
import pt.isel.ls.view.common.elements.Th;
import pt.isel.ls.view.common.elements.Thead;
import pt.isel.ls.view.common.elements.Title;
import pt.isel.ls.view.common.elements.Tr;

import java.util.ArrayList;
import java.util.List;

public class GetAllUsersResult extends CommandResult {

    private final List<Model> users;

    public GetAllUsersResult(List<Model> users) {
        this.users = users;
    }

    @Override
    public String printHtml() {
        ArrayList<Element> rows = new ArrayList<>();
        for (Model m: users) {
            User u = (User) m;
            rows.add(
                    new Tr(
                        new Td(new A(u.getName(), "/users/" + u.getId()))
                    )
            );
        }
        Html h = new Html(
                new Head(
                        new Title("Users List")
                ),
                new Body(
                        new A("Home", "/"),
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
                        )
                )
        );
        return h.print();
    }

    @Override
    public String printPlainText() {
        StringBuilder sb = new StringBuilder("All users -> \n");
        for (Model u : users) {
            User user = (User) u;
            sb.append("UserId = "
                    + user.getId()
                    + "\nName = "
                    + user.getName());
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public boolean asResult() {
        return !users.isEmpty();
    }

    @Override
    public Object getResult() {
        return users;
    }
}
