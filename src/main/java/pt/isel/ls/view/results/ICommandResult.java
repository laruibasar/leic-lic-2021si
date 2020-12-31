package pt.isel.ls.view.results;

import pt.isel.ls.config.RouterException;

public interface ICommandResult {

    public String printHtml() throws RouterException;

    public String printPlainText() throws RouterException;
}
