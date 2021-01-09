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
}
