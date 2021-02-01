package pt.isel.ls.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.AppCommand;
import pt.isel.ls.config.AppConfig;
import pt.isel.ls.config.RouterException;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.results.RateMovieResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.Header;
import pt.isel.ls.view.common.IView;
import pt.isel.ls.view.common.ViewRouter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListenHttpServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(ListenHttpServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int statusCode = 200;
        String respBody = "";
        Command cmd = new Command();
        CommandResult cr;
        Map<String, String[]> parameters = req.getParameterMap();

        log.info("incoming request: method={}, uri={}, accept={}",
                req.getMethod(),
                req.getRequestURI(),
                req.getHeader("Accept"));

        try {
            cmd = AppCommand.setCommand(
                    set(req.getMethod(), req.getRequestURI(), parameters)
            );

            cr = AppCommand.runCommand(cmd);

            if (cr.asResult()) {
                try {
                    ViewRouter viewRouter = AppConfig.getViewRouter();
                    IView view = viewRouter.findView(
                            new Header("accept:text/html"),
                            cr);
                    respBody = view.print(cmd, cr);
                } catch (Exception e) {
                    statusCode = 404;
                    respBody = "Resource not found: " + e.getMessage();
                }
            } else {
                statusCode = 404;
                respBody = "Resource not found";
            }
        } catch (RouterException e) {
            statusCode = 404;
            respBody = "Resource not found: " + e.getMessage();
        } catch (HandlerException e) {
            statusCode = 500;
            respBody = "Internal Error: " + e.getMessage();
        }

        //Format response body to submit the View of the CommandResult
        Charset utf8 = StandardCharsets.UTF_8;
        resp.setContentType(String.format("text/html; charset=%s", utf8.name()));

        //No need to change
        byte[] respBodyBytes = respBody.getBytes(utf8);
        resp.setStatus(statusCode);
        resp.setContentLength(respBodyBytes.length);
        OutputStream os = resp.getOutputStream();
        os.write(respBodyBytes);
        os.flush();
        log.info("outgoing response: method={}, uri={}, status={}, Content-Type={}",
                req.getMethod(),
                req.getRequestURI(),
                resp.getStatus(),
                resp.getHeader("Content-Type"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int statusCode = 200;
        String respBody = "";
        Command cmd = new Command();
        CommandResult cr;
        Map<String, String[]> parameters = req.getParameterMap();

        log.info("incoming request: method={}, uri={}, accept={}",
                req.getMethod(),
                req.getRequestURI(),
                req.getHeader("Accept"));

        try {
            cmd = AppCommand.setCommand(
                    set(req.getMethod(), req.getRequestURI(), parameters)
            );

            cr = AppCommand.runCommand(cmd);

            if (cr.asResult()) {
                resp.setStatus(303);
                /* This is a problem because on this special case, we don't go
                 * to the rating ID.
                 */
                if (cr.getClass() == RateMovieResult.class) {
                    resp.setHeader("Location", req.getRequestURI() + "/");
                } else {
                    resp.setHeader("Location", req.getRequestURI() + "/" + cr.getResultId());
                }
            } else {
                statusCode = 404;
                respBody = "Resource not found";
            }
        } catch (RouterException e) {
            statusCode = 404;
            respBody = "Resource not found";
        } catch (HandlerException e) {
            statusCode = 500;
            respBody = "Internal Error";
        }

        log.info("outgoing response: status={}, Location={}",
                resp.getStatus(),
                resp.getHeader("Location"));
    }

    private String[] setString(String method, String uri) {
        String[] splitUri = uri.split("&", 2);
        List<String> list = new ArrayList<>();
        list.add(method);
        list.add(splitUri[0]);
        list.add("accept:text/html");
        if (splitUri.length > 1) {
            list.add(splitUri[1]);
        }

        return list.toArray(new String[0]);
    }

    private String[] set(String method, String uri, Map<String, String[]> parameters) {
        if (parameters == null) {
            return setString(method, uri);
        }

        StringBuilder sb = new StringBuilder();
        for (String name : parameters.keySet()) {
            sb.append(name);
            sb.append("=");
            String[] values = parameters.get(name);
            sb.append(values[0]);
            sb.append("&");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        String[] command = setString(method, uri);
        List<String> list = Arrays.asList(command);
        ArrayList<String> result = new ArrayList<>();
        result.addAll(list);
        result.add(sb.toString());

        return result.toArray(new String[0]);
    }
}