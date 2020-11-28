package pt.isel.ls.data.transaction;

import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;

import java.sql.Connection;
import java.util.LinkedList;

public class DataTransaction extends Data implements IDataTransaction {
    public LinkedList<Model> executeTransaction(IDataFunction fun) throws DataConnectionException {
        Connection conn = null;
        LinkedList<Model> models = new LinkedList<>();

        try {
            conn = getDataConnection().getConnection();
            conn.setAutoCommit(false);

            models = fun.execute(conn);

            conn.commit();
        } catch (Exception e) {
            rollbackConnection(conn);
            throw new DataConnectionException("Unable to execute transaction "
                    + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }

        return models;
    }
}