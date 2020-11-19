package pt.isel.ls.services.common;

import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

public interface IHandler {
    public CommandResult execute(Command cmd) throws DataConnectionException,
            HandlerException;
}
