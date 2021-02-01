package pt.isel.ls.view.text;

import pt.isel.ls.model.User;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;

public class CreateUserTextView extends TextView implements IView {


    @Override
    public String print(Command cmd, CommandResult cr) {
        User user = (User) cr.getResult();
        return user == null ? "User not created" : "Created User -> "
                + "\nUserid = "
                + user.getId()
                + "\nName = "
                + user.getName()
                + "\nEmail ="
                + user.getEmail()
                + "\n";
    }
}
