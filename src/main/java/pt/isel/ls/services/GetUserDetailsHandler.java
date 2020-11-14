package pt.isel.ls.services;

import pt.isel.ls.data.IUserData;
import pt.isel.ls.data.UserData;
import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.User;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.sql.SQLException;


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
