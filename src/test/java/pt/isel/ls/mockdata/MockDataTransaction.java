package pt.isel.ls.mockdata;

import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.data.transaction.IDataFunction;
import pt.isel.ls.data.transaction.IDataTransaction;
import pt.isel.ls.model.Model;

import java.sql.Connection;
import java.util.LinkedList;

public class MockDataTransaction extends Data implements IDataTransaction {
    public LinkedList<Model> executeTransaction(IDataFunction fun) throws DataConnectionException {
        Connection conn = null;
        LinkedList<Model> models = new LinkedList<>();

        try {
            conn = getDataConnection().getConnection();
            conn.setAutoCommit(false);

            models = fun.execute(conn);
        } catch (Exception e) {
            throw new DataConnectionException("Unable to execute transaction "
                    + e.getMessage(), e);
        } finally {
            rollbackConnection(conn);
            closeConnection(conn);
        }

        return models;
    }
}
