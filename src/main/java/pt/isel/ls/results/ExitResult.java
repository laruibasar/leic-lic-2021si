package pt.isel.ls.results;

public class ExitResult extends CommandResult {
    @Override
    public String printHtml() {
        return null;
    }

    @Override
    public String printPlainText() {
        return "Exit program";
    }

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
