package pt.isel.ls.view.text;

import pt.isel.ls.config.Router;
import pt.isel.ls.config.RouterException;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;

import java.util.ArrayList;

public class OptionTextView extends TextView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) throws RouterException {
        Router router = (Router) cr.getResult();

        StringBuilder sb = new StringBuilder();
        for (Command c : router.getTree().getAllCommands()) {
            sb.append("COMMAND: ")
                    .append(router.findHandler(c).getDescription())
                    .append("\n")
                    .append(c.toString())
                    .append("\n");
            ArrayList<String> params = null;
            params = (ArrayList<String>) router.findHandler(c).getValidValues();

            if (params.size() > 0) {
                sb.append("Parâmetros aceites:\n");
                for (String s : params) {
                    sb.append("\t")
                            .append(s)
                            .append("\n");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
