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
import java.time.Instant;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListenHttpServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(ListenHttpServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        log.info("incoming request: method={}, uri={}, accept={}",
                req.getMethod(),
                req.getRequestURI(),
                req.getHeader("Accept"));

        try {
            Command cmd = AppCommand.setCommand(new String[] {
                req.getMethod(),
                req.getRequestURI(),
                "accept:text/html"
            });

            CommandResult cr = AppCommand.runCommand(cmd);
        } catch (RouterException | HandlerException e) {
            e.printStackTrace();
        }

        //View view = View.findView(comandResult);

        //Format response body to submit the View of the CommandResult
        Charset utf8 = StandardCharsets.UTF_8;
        resp.setContentType(String.format("text/plain; charset=%s", utf8.name()));
        String respBody = String.format("Current date and time is %s", Instant.now());

        //No need to change
        byte[] respBodyBytes = respBody.getBytes(utf8);
        resp.setStatus(200);
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
}