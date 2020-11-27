package pt.isel.ls.view.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.User;
import pt.isel.ls.view.html.body.Body;
import pt.isel.ls.view.html.body.Table;
import pt.isel.ls.view.html.head.Head;
import pt.isel.ls.view.html.head.Title;
import pt.isel.ls.view.html.Html;

import java.util.ArrayList;
import java.util.List;

public class GetUserDetailsResult extends CommandResult {

    private User user;

    public GetUserDetailsResult(List<Model> users) {
        if(users.size() != 1 || !(users instanceof User)){
            //create exception
        }

        this.user = (User) users.get(0);
    }

    @Override
    public String printHTML() {
        ArrayList<String[]> rows = new ArrayList<>();
        rows.add(new String[] {
                String.valueOf(user.getId()),
                user.getName(),
                user.getEmail()});

        Html h = new Html(
                new Head(
                        new Title("User details")
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
        return "User details -> " +
                user.toString();
    }
}
