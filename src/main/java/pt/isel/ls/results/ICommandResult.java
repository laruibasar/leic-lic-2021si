package pt.isel.ls.results;

import pt.isel.ls.config.RouterException;

public interface ICommandResult {

    public String printHtml() throws RouterException;

    public String printPlainText() throws RouterException;

    public boolean asResult();
}
