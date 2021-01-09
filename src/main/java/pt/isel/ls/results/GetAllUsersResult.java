package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.User;

import java.util.ArrayList;
import java.util.List;
import pt.isel.ls.view.htmlold.Html;
import pt.isel.ls.view.htmlold.body.Body;
import pt.isel.ls.view.htmlold.body.Table;
import pt.isel.ls.view.htmlold.head.Head;
import pt.isel.ls.view.htmlold.head.Title;

public class GetAllUsersResult extends CommandResult {

    private final List<Model> users;

    public GetAllUsersResult(List<Model> users) {

        this.users = users;
    }


    @Override
    public String printHtml() {
        ArrayList<String> header = new ArrayList<>();
        header.add("User ID");
        header.add("Name");

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
                                header,
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
            User user = (User) u;
            sb.append("UserId = "
                    + user.getId()
                    + "\nName = "
                    + user.getName());
            sb.append('\n');
        }
        return sb.toString();
    }
}
