package pt.isel.ls.results;

import pt.isel.ls.config.Router;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.html.Html;
import pt.isel.ls.view.html.body.Body;
import pt.isel.ls.view.html.body.Bullets;
import pt.isel.ls.view.html.head.Head;
import pt.isel.ls.view.html.head.Title;

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
            sb.append(router.getHandler(c).getDescription() + "\n"
                    + "Command: " + c.toString() + "\n\n");
        }
        return sb.toString();
    }
}
