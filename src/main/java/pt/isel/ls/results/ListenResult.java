package pt.isel.ls.results;

import pt.isel.ls.config.AppConfig;
import pt.isel.ls.http.AppHttpServlet;

public class ListenResult extends CommandResult {
    public ListenResult() throws Exception {
        AppHttpServlet http = AppConfig.getHttp();
        try {
            http.start();
        } catch (Exception e) {
            throw new Exception("HTTP Servlet: " + e.getMessage(), e);
        }
    }

    @Override
    public String printHtml() {
        return null;
    }

    @Override
    public String printPlainText() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
