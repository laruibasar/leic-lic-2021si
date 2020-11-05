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

    public CommandResult(LinkedList<Model> commandResults, int status) {
        this.commandResults = commandResults;
        this.status = status;
    }

    public CommandResult(int status) {
        this.status = status;
    }

    public void printResults() {
        if (commandResults.size() > 0) {
            for (Model cr : commandResults) {
                System.out.println(cr.toString());
            }
        }
    }
}