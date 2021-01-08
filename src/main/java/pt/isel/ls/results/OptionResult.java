package pt.isel.ls.results;

import pt.isel.ls.config.Router;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.htmlold.Html;
import pt.isel.ls.view.htmlold.body.Body;
import pt.isel.ls.view.htmlold.body.Bullets;
import pt.isel.ls.view.htmlold.head.Head;
import pt.isel.ls.view.htmlold.head.Title;

import java.util.ArrayList;

public class OptionResult extends CommandResult {

    private Router router;

    public OptionResult(Router router) {
        this.router = router;
    }

    @Override
    public String printHtml() {
        ArrayList<String> arrayList = new ArrayList<>();

        for (Command c : router) {
            arrayList.add(router.getHandler(c).getDescription() + "\n"
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
    public String printPlainText() {
        StringBuilder sb = new StringBuilder();
        for (Command c : router) {
            sb.append("COMMAND: ")
                    .append(router.getHandler(c).getDescription())
                    .append("\n")
                    .append(c.toString())
                    .append("\n");
            ArrayList<String> params = (ArrayList<String>) router.getHandler(c).getValidValues();

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
