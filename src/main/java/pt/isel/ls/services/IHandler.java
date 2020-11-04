package pt.isel.ls.services;

import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.EmptyResult;

import java.sql.SQLException;

public interface IHandler {
    public CommandResult execute(Command cmd) throws DataConnectionException,
            SQLException, EmptyResult;
}
