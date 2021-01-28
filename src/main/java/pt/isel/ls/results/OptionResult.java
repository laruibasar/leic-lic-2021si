package pt.isel.ls.results;

import pt.isel.ls.config.Router;
import pt.isel.ls.config.RouterException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.htmlold.Html;
import pt.isel.ls.view.htmlold.body.Body;
import pt.isel.ls.view.htmlold.body.Bullets;
import pt.isel.ls.view.htmlold.head.Head;
import pt.isel.ls.view.htmlold.head.Title;


import java.util.ArrayList;

public class OptionResult extends CommandResult {

    private Router router;

    public OptionResult() {
        router = new Router();
    }

    public OptionResult(Router router) {
        this.router = router;
    }

    @Override
    public String printHtml() throws RouterException {
        ArrayList<String> arrayList = new ArrayList<>();

        for (Command c : router.getTree().getAllCommands()) {
            arrayList.add(router.findHandler(c).getDescription() + "\n"
                    + "Command: " + c.toString() + "\n");
        }

        String[] bullets = new String[]{};
        arrayList.toArray(bullets);

        Html h = new Html(
                new Head(
                        new Title("All Commands")
                ),
                new Body(
                        new Bullets(
                                bullets
                        )
                )
        );
        return h.toString();
    }

    @Override
    public String printPlainText() throws RouterException {
        StringBuilder sb = new StringBuilder();
        for (Command c : router.getTree().getAllCommands()) {
            sb.append("COMMAND: ")
                    .append(router.findHandler(c).getDescription())
                    .append("\n")
                    .append(c.toString())
                    .append("\n");
            ArrayList<String> params = (ArrayList<String>) router.findHandler(c).getValidValues();

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

    @Override
    public boolean asResult() {
        return false;
    }

    @Override
    public Object getResult() {
        return router;
    }

    @Override
    public int getResultId() {
        return 0;
    }
}
