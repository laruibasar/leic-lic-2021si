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
import pt.isel.ls.view.results.GetAllUsersResult;

import java.util.LinkedList;

/**
 * GET /users - returns the list of users.
 */
public class GetAllUsersHandler extends Handler implements IHandler {
    IUserData userData;

    public GetAllUsersHandler() {
        super();
        userData = new UserData();
        description = "Returns the list of users";
    }

    // to use for testing mainly
    public void setUserDataConnection(IUserData userData) {
        this.userData = userData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        try {
            LinkedList<Model> result = ts.executeTransaction((connection) -> {
                return userData.getAllUsers(connection);
            });

            return new GetAllUsersResult(result);
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
