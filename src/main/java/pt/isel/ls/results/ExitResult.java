package pt.isel.ls.results;

import pt.isel.ls.config.AppConfig;
import pt.isel.ls.http.AppHttpServlet;

public class ExitResult extends CommandResult {
    public ExitResult() throws Exception {
        AppHttpServlet http = AppConfig.getHttp();
        try {
            if (http.isStarted()) {
                http.stop();
            }
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
}
