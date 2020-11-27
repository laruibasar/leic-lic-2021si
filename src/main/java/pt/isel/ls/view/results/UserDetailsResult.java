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

public class UserDetailsResult extends CommandResult {

    private User user;

    public UserDetailsResult(List<Model> user) {
        if(user.size() != 1 || !(user instanceof User)){
            //create exception
        }

        this.user = (User) user.get(0);
    }

    @Override
    public String printHTML() {
        ArrayList<String[]> rows = new ArrayList<>();
        rows.add(new String[] {
                "" + user.getId(),
                "" + user.getName(),
                "" + user.getEmail()});
        //StringBuilder sb = new StringBuilder();
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
        return "" +
                user.getId() +
                " " +
                user.getName() +
                " " +
                user.getEmail();
    }
}
