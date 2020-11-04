package pt.isel.ls.utils;

import pt.isel.ls.model.Model;
import java.util.LinkedList;

/**
 *  class responsible for saving the result of the query made to the database
 *  The ResultSet interface provides getter methods for retrieving column values
 *  from the current row.
 */

public class CommandResult {

    private LinkedList<Model> commandResults;
    private int status;

    public CommandResult(LinkedList<Model> commandResults) throws EmptyResult {
        if (commandResults == null) {
            throw new EmptyResult("empty result");
        }
        this.commandResults = commandResults;
    }

    public CommandResult(int status) throws FailedCommand {
        if (status == 0) {
            throw new FailedCommand("The command wasn't executed properly");
        }
        this.status = status;
    }

    public void printResults() throws EmptyResult {
        if (commandResults.size() > 0) {
            for (Model cr : commandResults) {
                System.out.println(cr.toString());
            }
        } else {
            throw new EmptyResult("Empty result");
        }
    }
}