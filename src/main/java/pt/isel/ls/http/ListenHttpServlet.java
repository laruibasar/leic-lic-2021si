package pt.isel.ls.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.AppCommand;
import pt.isel.ls.config.RouterException;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
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

        log.info("incoming request: method={}, uri={}, accept={}",
                req.getMethod(),
                req.getRequestURI(),
                req.getHeader("Accept"));

        try {
            cmd = AppCommand.setCommand(
                    setString(req.getMethod(), req.getRequestURI()
            ));

            cr = AppCommand.runCommand(cmd);

            if (cr.asResult()) {
                // TODO: add separate views from CommandResult
                //View view = View.findView(cr);
                respBody = cr.printHtml();
            } else {
                statusCode = 404;
                respBody = "Resource not found";
            }
        } catch (RouterException e) {
            statusCode = 400;
            respBody = "Bad request";
        } catch (HandlerException e) {
            statusCode = 500;
            respBody = "Internal Error";
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

    private String[] setString(String method, String uri) {
        String[] splitUri = uri.split("&", 2);
        List<String> list = new ArrayList<String>();
        list.add(method);
        list.add(splitUri[0]);
        list.add("accept:text/html");
        if (splitUri.length > 1) {
            list.add(splitUri[1]);
        }

        return list.toArray(new String[0]);
    }
}