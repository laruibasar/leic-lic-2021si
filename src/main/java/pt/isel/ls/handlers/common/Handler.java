package pt.isel.ls.handlers.common;

import pt.isel.ls.data.transaction.DataTransaction;
import pt.isel.ls.data.transaction.IDataTransaction;
import pt.isel.ls.utils.Command;

public abstract class Handler implements IHandler {
    protected Command template;
    protected IDataTransaction ts;

    public Handler() {
        template = new Command();
        ts = new DataTransaction();
    }

    public Command getTemplate() {
        return template;
    }

    public void setDataTransaction(IDataTransaction ts) {
        this.ts = ts;
    }
}
