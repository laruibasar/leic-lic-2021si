
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
import pt.isel.ls.view.PrintResults;
import pt.isel.ls.view.results.CommandResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class PrintResultsTest {

    public PrintResultsTest() {
        AppConfig.setup();
    }

    @Test
    public void test_file_output() throws HandlerException {
        Handler handler = new GetMoviesHandler();
        Command cmd = new Command(Method.GET,
                new Path("/movies"),
                new Header("accept:text/plain|file-name:"));
        handler.setDataTransaction(new MockDataTransaction());
        CommandResult cr = handler.execute(cmd);
        PrintResults pr = new PrintResults(cr, cmd.getHeader());
        final String aux = "Top Ratings -> \n"
                + "MovieID = 1   Title = Gladiator Year = 2000\n"
                + "MovieID = 2   Title = The Fast and the Furious Year = 2001\n"
                + "MovieID = 3   Title = Finding Nemo Year = 2003\n"
                + "MovieID = 4   Title = The Godfather: part III Year = 1990\n"
                + "MovieID = 5   Title = The Godfather Year = 1972\n";
        assertEquals(pr.toString(), aux);
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