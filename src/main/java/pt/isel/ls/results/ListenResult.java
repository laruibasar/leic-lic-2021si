package pt.isel.ls.results;

public class ListenResult extends CommandResult {
    @Override
    public String printHtml() {
        return null;
    }

    @Override
    public String printPlainText() {
        return "Server started";
    }

    @Override
    public boolean asResult() {
        return false;
    }
}
