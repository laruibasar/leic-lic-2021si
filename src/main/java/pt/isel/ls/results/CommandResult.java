package pt.isel.ls.results;

public abstract class CommandResult implements ICommandResult {
    public abstract Object getResult();

    public abstract int getResultId();
}