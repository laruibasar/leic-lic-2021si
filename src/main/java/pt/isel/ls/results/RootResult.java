package pt.isel.ls.results;

public class RootResult extends CommandResult {
    @Override
    public boolean asResult() {
        return true;
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
