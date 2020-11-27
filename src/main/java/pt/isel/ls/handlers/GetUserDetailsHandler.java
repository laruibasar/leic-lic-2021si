package pt.isel.ls.handlers;

import pt.isel.ls.data.IUserData;
import pt.isel.ls.data.UserData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

/**
 * GET /users/{uid} - returns the details for the user identified by uid
 */
public class GetUserDetailsHandler extends Handler implements IHandler {
    IUserData userData;

    public GetUserDetailsHandler() {
        super();
        userData = new UserData();
    }

    public void setUserDataConnection(IUserData userData) {
        this.userData = userData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        try {
            return userData.getUser(Integer.parseInt(cmd.getPath().getValue(1)));
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}