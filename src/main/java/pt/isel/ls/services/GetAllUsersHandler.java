package pt.isel.ls.services;

import pt.isel.ls.data.IUserData;
import pt.isel.ls.data.UserData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

/**
 * GET /users - returns the list of users.
 */

public class GetAllUsersHandler extends Handler implements IHandler {
    IUserData userData;

    public GetAllUsersHandler() {
        super();
        userData = new UserData();
    }

    // to use for testing mainly
    public void setUserData(IUserData userData) {
        this.userData = userData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        try {
            return userData.getAllUsers();
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
