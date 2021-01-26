package pt.isel.ls.results;

public class ListenResult extends CommandResult {
    @Override
    public boolean asResult() {
        return false;
    }

    @Override
    public Object getResult() {
        return null;
    }

    @Override
    public int getResultId() {
        return 0;
    }
}
