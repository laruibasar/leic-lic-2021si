package pt.isel.ls.view.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.User;

import java.util.ArrayList;
import java.util.List;
import pt.isel.ls.view.html.Html;
import pt.isel.ls.view.html.body.Body;
import pt.isel.ls.view.html.body.Table;
import pt.isel.ls.view.html.head.Head;
import pt.isel.ls.view.html.head.Title;

public class GetAllUsersResult extends CommandResult {

    private final List<Model> users;

    public GetAllUsersResult(List<Model> users) {

        this.users = users;
    }


    @Override
    public String printHtml() {
        ArrayList<String[]> rows = new ArrayList<>();
        for (Model m: users) {
            User u = (User) m;
            rows.add(
                    new String[] {
                            String.valueOf(u.getId()),
                            u.getName()
                    }
            );
        }
        Html h = new Html(
                new Head(
                        new Title("Users List")
                ),
                new Body(
                        new Table(
                                rows
                        )
                )

        );
        return h.toString();
    }

    @Override
    public String printPlainText() {
        StringBuilder sb = new StringBuilder("All users -> \n");
        for (Model u : users) {
            sb.append("UserId = "
                    + ((User)u).getId()
                    + "\nName = "
                    + ((User)u).getName());
            sb.append('\n');
        }
        return sb.toString();
    }
}
