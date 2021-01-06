package pt.isel.ls.handlers.common;

import pt.isel.ls.data.transaction.DataTransaction;
import pt.isel.ls.data.transaction.IDataTransaction;
import pt.isel.ls.utils.Command;

import java.util.ArrayList;
import java.util.List;

public abstract class Handler implements IHandler {
    protected String description;
    protected List<String> validValues;
    protected IDataTransaction ts;

    public Handler() {
        ts = new DataTransaction();
        validValues = new ArrayList<>();
    }

    public void setDataTransaction(IDataTransaction ts) {
        this.ts = ts;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getValidValues() {
        return validValues;
    }

    protected String checkNeededValues(Command cmd) throws HandlerException {
        StringBuilder needed = new StringBuilder();
        for (String key : validValues) {
            if (cmd.getValue(key) == null) {
                needed.append("\"").append(key).append("\" ");
            }
        }

        return needed.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String key : validValues) {
            str.append(key).append(" ");
        }

        return str.toString() + description;
    }
}
