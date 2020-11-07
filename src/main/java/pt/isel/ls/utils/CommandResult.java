package pt.isel.ls.utils;

import pt.isel.ls.model.Model;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *  Save the command result from the execution by the handler.
 */

public class CommandResult implements Iterable<Model> {

    private LinkedList<Model> commandResults;
    private int status;

    public CommandResult(LinkedList<Model> commandResults, int status) {
        this.commandResults = commandResults;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public Iterator<Model> iterator() {
        return commandResults.iterator();
    }
}