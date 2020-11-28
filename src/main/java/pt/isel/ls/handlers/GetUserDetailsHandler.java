package pt.isel.ls.handlers;

import pt.isel.ls.data.IUserData;
import pt.isel.ls.data.UserData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.model.Model;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.results.CommandResult;
import pt.isel.ls.view.results.GetUserDetailsResult;

import java.util.LinkedList;

/**
 * GET /users/{uid} - returns the details for the user identified by uid
 */
public class GetUserDetailsHandler extends Handler implements IHandler {
    IUserData userData;

    public GetUserDetailsHandler() {
        super();
        userData = new UserData();
        description = "Return the details for the user identified by uid";

        validValues.add("uid");
    }

    public void setUserDataConnection(IUserData userData) {
        this.userData = userData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        String check = checkNeededValues(cmd);
        if (check.length() > 0) {
            throw new HandlerException("Handler missing parameters: "
                    + check);
        }

        int user;
        try {
            user = Integer.parseInt(cmd.getValue("uid"));
        } catch (NumberFormatException e) {
            throw new HandlerException("Handler invalid format for uid: "
                    + cmd.getValue("uid"));
        }

        try {
            LinkedList<Model> result = ts.executeTransaction((connection) -> {
                return userData.getUser(connection, user);
            });

            return new GetUserDetailsResult(result);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
