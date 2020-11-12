package pt.isel.ls.data;

import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.utils.CommandResult;

public interface IUserData {
    public CommandResult createUser(String name, String email);

    public CommandResult getAllUsers() throws DataConnectionException;

    public CommandResult getUser(int id);
}
