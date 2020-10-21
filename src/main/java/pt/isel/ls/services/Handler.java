package pt.isel.ls.services;

public abstract class Handler {
    public int execute() {
        return 0;
    }
}

interface CommandHadler{
    CommandResult execute(CommandRequest commandRequest);
}
