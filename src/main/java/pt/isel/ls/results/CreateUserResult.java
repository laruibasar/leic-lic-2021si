package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.User;
import pt.isel.ls.view.html.body.Body;
import pt.isel.ls.view.html.body.Table;
import pt.isel.ls.view.html.head.Head;
import pt.isel.ls.view.html.head.Title;
import pt.isel.ls.view.html.Html;

import java.util.ArrayList;
import java.util.List;

public class CreateUserResult extends CommandResult {

    private User user;

    public CreateUserResult(List<Model> users) {
        if (users.size() != 0) {
            this.user = (User) users.get(0);
        }


    }

    @Override
    public String printHtml() {
        ArrayList<String> header = new ArrayList<>();
        header.add("User Id");
        header.add("Name");
        header.add("Email");

        ArrayList<String[]> rows = new ArrayList<>();
        String title = "Created user";

        if (user == null) {
            title = "User not created";
        } else {
            rows.add(new String[] {
                    String.valueOf(user.getId()),
                    user.getName(),
                    user.getEmail()});
        }


        Html h = new Html(
                new Head(
                        new Title(title)
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
        return user == null ? "User not created" : "Created User -> "
                + "\nUserid = "
                + user.getId()
                + "\nName = "
                + user.getName()
                + "\nEmail ="
                + user.getEmail();
    }
}
