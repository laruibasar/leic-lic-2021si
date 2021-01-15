package pt.isel.ls.view.common;

import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Header;

import java.util.HashMap;
import java.util.Map;

public class ViewRouter {
    private Map<Class<? extends CommandResult>, IView> htmlRouter = new HashMap<>();
    private Map<Class<? extends CommandResult>, IView> textRouter = new HashMap<>();

    public void addView(Header header, CommandResult cr, IView view) {
        switch (header.getValue("accept")) {
            case "text/plain":
                textRouter.put(cr.getClass(), view);
                break;
            case "text/html":
                htmlRouter.put(cr.getClass(), view);
                break;
        }
    }

    public IView findView(Header header, CommandResult cr) throws Exception {
        switch (header.getValue("accept")) {
            case "text/plain":
                return textRouter.get(cr.getClass());
            case "text/html":
                return htmlRouter.get(cr.getClass());
            default:
                throw new Exception("View: no view available");
        }
    }
}
