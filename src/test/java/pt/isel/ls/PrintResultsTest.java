package pt.isel.ls;

import org.junit.Test;
import pt.isel.ls.config.AppConfig;
import pt.isel.ls.handlers.GetMoviesHandler;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.mockdata.MockDataTransaction;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.Header;
import pt.isel.ls.utils.Method;
import pt.isel.ls.utils.Path;

import java.io.IOException;
import java.util.LinkedList;

public class PrintResultsTest {

    public PrintResultsTest() {
        AppConfig.setup();
    }

    @Test(expected = IOException.class)
    public void test_file_output() {
        Handler handler = new GetMoviesHandler();
        Command cmd = new Command(Method.GET,
                new Path("/movies/1"),
                new Header("accept:text/plain|file-name:noExtensionFile"));
        handler.setDataTransaction(new MockDataTransaction());
        CommandResult cr = new Command(cmd);
        PrintResults pr = new PrintResults(cr, cmd.getHeader());
    }

    @Test
    public void file_hmtl_print() {
        File file = new File("test.txt");

    }

}