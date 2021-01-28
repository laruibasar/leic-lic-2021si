package pt.isel.ls.view.common;

import pt.isel.ls.config.RouterException;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;

public interface IView {
    public String print(Command cmd, CommandResult cr) throws RouterException;
}
