package pt.isel.ls.handlers.common;

import pt.isel.ls.utils.Command;

import java.util.ArrayList;
import java.util.List;

public abstract class Handler implements IHandler {
    protected String description;
    protected List<String> validValues;

    public Handler() {
        validValues = new ArrayList<>();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    protected String checkNeededValues(Command cmd) throws HandlerException {
        StringBuilder keys = new StringBuilder();
        for (String key : validValues) {
            if (cmd.getValue(key) == null) {
                keys.append("\"").append(key).append("\" ");
            }
        }

        return keys.toString();
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
