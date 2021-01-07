package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.User;
import pt.isel.ls.view.common.A;
import pt.isel.ls.view.common.Body;
import pt.isel.ls.view.common.Element;
import pt.isel.ls.view.common.Head;
import pt.isel.ls.view.common.Html;
import pt.isel.ls.view.common.Table;
import pt.isel.ls.view.common.Tbody;
import pt.isel.ls.view.common.Td;
import pt.isel.ls.view.common.Text;
import pt.isel.ls.view.common.Th;
import pt.isel.ls.view.common.Thead;
import pt.isel.ls.view.common.Title;
import pt.isel.ls.view.common.Tr;

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
                        new Td(new A(String.valueOf(u.getId()), "https://localhost/users/" + u.getId()), new Text(u.getName()))
                    )
            );
        }
        Html h = new Html(
                new Head(
                        new Title("Users List")
                ),
                new Body(
                        new Table(
                                new Tr(
                                        new Thead(
                                                new Tr(
                                                        new Th("User ID"),
                                                        new Th("Name")
                                                )
                                        )
                                ),
                                new Tr(
                                        new Tbody(
                                                rows
                                        )
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
            sb.append(u.toString());
            sb.append('\n');
        }
        return sb.toString();
    }
}
