package pt.isel.ls.data.transaction;

import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;

import java.util.LinkedList;

public interface IDataTransaction {
    public LinkedList<Model> executeTransaction(IDataFunction fun) throws DataConnectionException;
}
