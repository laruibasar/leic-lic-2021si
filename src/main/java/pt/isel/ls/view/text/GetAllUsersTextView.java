package pt.isel.ls.view.text;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.User;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;

import java.util.LinkedList;

public class GetAllUsersTextView extends TextView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        LinkedList<Model> users = (LinkedList<Model>) cr.getResult();

        StringBuilder sb = new StringBuilder("All users: \n");
        for (Model u : users) {
            User user = (User) u;
            sb.append("UserId: ").append(user.getId()).append("\t");
            sb.append("Name = ").append(user.getName()).append('\n');
        }

        return sb.toString();
    }
}
