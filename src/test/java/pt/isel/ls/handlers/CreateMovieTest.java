package pt.isel.ls.handlers;

import org.junit.Test;

import pt.isel.ls.config.AppConfig;
import pt.isel.ls.mockdata.MockDataTransaction;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.results.CommandResult;
import pt.isel.ls.utils.Method;
import pt.isel.ls.utils.Parameters;
import pt.isel.ls.utils.Path;

//import static org.junit.Assert.assertEquals;

public class CreateMovieTest {

    @Test
    public void insert_movie_test_transaction() throws HandlerException {
        AppConfig.setup();

        Parameters params = new Parameters();
        params.setValues("title=The+Godfather:+part+II&releaseYear=1974");
        Command cmd = new Command(Method.POST, new Path("/movies"), params);

        CreateMovieHandler handler = new CreateMovieHandler();
        handler.setDataTransaction(new MockDataTransaction());
        CommandResult rs = handler.execute(cmd);

        /*
        assertEquals(1, rs.getStatus());
        for (Model test : rs) {
            Movie testMovie = (Movie) test;
            assertEquals("The Godfather: part II", testMovie.getTitle());
            assertEquals(1974, testMovie.getYear());
        }*/
    }

    @Test
    public void insert_movie_test() throws HandlerException {
        AppConfig.setup();

        Parameters params = new Parameters();
        params.setValues("title=The+Godfather:+part+II&releaseYear=1974");
        Command cmd = new Command(Method.POST, new Path("/movies"), params);

        CreateMovieHandler handler = new CreateMovieHandler();
        handler.setDataTransaction(new MockDataTransaction());
        CommandResult rs = handler.execute(cmd);

        /*assertEquals(1, rs.getStatus());
        for (Model test : rs) {
            Movie testMovie = (Movie) test;
            assertEquals("The Godfather: part II", testMovie.getTitle());
            assertEquals(1974, testMovie.getYear());
        }*/
    }

    @Test (expected = HandlerException.class)
    public void insert_duplicate_year_title_fail() throws HandlerException {
        AppConfig.setup();

        Parameters params = new Parameters();
        params.setValues("title=The+Godfather&releaseYear=1972");
        Command cmd = new Command(Method.POST, new Path("/movies"), params);

        CreateMovieHandler handler = new CreateMovieHandler();
        handler.setDataTransaction(new MockDataTransaction());

        CommandResult rs = handler.execute(cmd); // expect fail here
    }

    @Test (expected = HandlerException.class)
    public void missing_parameters() throws HandlerException {
        AppConfig.setup();

        Parameters params = new Parameters();
        params.setValues("title=The+Godfather:+part+II");
        Command cmd = new Command(Method.POST, new Path("/movies"), params);

        CreateMovieHandler handler = new CreateMovieHandler();
        handler.setDataTransaction(new MockDataTransaction());
        CommandResult rs = handler.execute(cmd); // expect fail here
    }
}
