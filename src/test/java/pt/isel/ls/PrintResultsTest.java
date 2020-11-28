
package pt.isel.ls;

import org.junit.Test;
import pt.isel.ls.config.AppConfig;
import pt.isel.ls.handlers.GetMoviesHandler;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.mockdata.MockDataTransaction;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.Header;
import pt.isel.ls.utils.Method;
import pt.isel.ls.utils.Path;
import pt.isel.ls.view.results.CommandResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PrintResultsTest {

    public PrintResultsTest() {
        AppConfig.setup();
    }

    //TODO: header accepts empty file-name
    @Test(expected = IOException.class)
    public void test_file_output() throws HandlerException {
        Handler handler = new GetMoviesHandler();
        Command cmd = new Command(Method.GET,
                new Path("/movies/1"),
                new Header("accept:text/plain|file-name:"));
        handler.setDataTransaction(new MockDataTransaction());
        CommandResult cr = handler.execute(cmd);
        PrintResults pr = new PrintResults(cr, cmd.getHeader());
        System.out.println(pr.toString());
    }

    @Test
    public void file_hmtl_print() throws HandlerException, IOException {
        byte[] path = Files.readAllBytes(Paths.get("//test.txt"));
        Handler handler = new GetMoviesHandler();
        Command cmd = new Command(Method.GET,
                new Path("/movies/1"),
                new Header("accept:text/plain|file-name:test1.txt"));
        handler.setDataTransaction(new MockDataTransaction());
        CommandResult cr = handler.execute(cmd);
        PrintResults pr = new PrintResults(cr, cmd.getHeader());
        System.out.println(pr.toString());

    }

}