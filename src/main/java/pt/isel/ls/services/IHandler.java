package pt.isel.ls.services;

import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.services.exceptions.InvalidAverageException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.ParametersExceptions;

import java.sql.SQLException;

public interface IHandler {
    public CommandResult execute(Command cmd) throws DataConnectionException,
            SQLException, InvalidAverageException, ParametersExceptions, HandlerException;
}
