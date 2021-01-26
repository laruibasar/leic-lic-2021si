package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.User;
import pt.isel.ls.view.htmlold.Html;
import pt.isel.ls.view.htmlold.body.Body;
import pt.isel.ls.view.htmlold.body.Table;
import pt.isel.ls.view.htmlold.head.Head;
import pt.isel.ls.view.htmlold.head.Title;

import java.util.ArrayList;
import java.util.List;

public class CreateUserResult extends CommandResult {

    private User user;

    public CreateUserResult(List<Model> users) {
        if (users.size() != 0) {
            this.user = (User) users.get(0);
        }
    }

    public CreateUserResult() {
    }

    @Override
    public boolean asResult() {
        return user != null;
    }

    @Override
    public Object getResult() {
        return user;
    }

    @Override
    public int getResultId() {
        return (user != null) ? user.getId() : 0;
    }
}
