package pt.isel.ls.data.transaction;

import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;

import java.sql.Connection;
import java.util.LinkedList;

public interface IDataFunction {
    public LinkedList<Model> execute(Connection connection) throws DataConnectionException;
}
