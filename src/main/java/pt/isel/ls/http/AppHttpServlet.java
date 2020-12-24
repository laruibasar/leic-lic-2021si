package pt.isel.ls.http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import pt.isel.ls.config.AppConfig;
import pt.isel.ls.config.HttpServletConfig;

public class AppHttpServlet {
    private static Server server;
    private static ServletHandler handler;
    private static ListenHttpServlet servlet;

    public AppHttpServlet() {
        HttpServletConfig config = AppConfig.getHttpServletConfig();
        server = new Server(config.getPort());
        handler = new ServletHandler();
        servlet = new ListenHttpServlet();

        handler.addServletWithMapping(new ServletHolder(servlet), "/*");
        server.setHandler(handler);
    }

    public void start() throws Exception {
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }
}