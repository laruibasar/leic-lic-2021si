package pt.isel.ls.services;

import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.utils.CommandResult;

import java.sql.SQLException;

public interface IHandler {
    public CommandResult execute() throws DataConnectionException, SQLException;
}
