package pt.isel.ls.data;

import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;

import java.sql.Connection;
import java.util.LinkedList;

public interface IUserData {
    public LinkedList<Model> createUser(Connection connection, String name, String email)
            throws DataConnectionException;

    public LinkedList<Model> getAllUsers(Connection connection)
            throws DataConnectionException;

    public LinkedList<Model> getUser(Connection connection, int id)
            throws DataConnectionException;
}
