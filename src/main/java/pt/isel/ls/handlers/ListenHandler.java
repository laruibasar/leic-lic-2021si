package pt.isel.ls.handlers;

import pt.isel.ls.config.AppConfig;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.http.AppHttpServlet;
import pt.isel.ls.results.ListenResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.results.CommandResult;

public class ListenHandler extends Handler implements IHandler {
    public ListenHandler() {
        super();
        description = "Start HTTP servlet";
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        try {
            AppHttpServlet http = AppConfig.getHttp();
            try {
                http.start();
            } catch (Exception e) {
                throw new Exception("HTTP Servlet: " + e.getMessage(), e);
            }
            return new ListenResult();
        } catch (Exception e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
