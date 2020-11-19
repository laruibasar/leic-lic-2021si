package pt.isel.ls.services;

import org.junit.Test;

import pt.isel.ls.config.AppConfig;
import pt.isel.ls.data.IMovieData;
import pt.isel.ls.data.MovieData;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Method;
import pt.isel.ls.utils.Parameters;
import pt.isel.ls.utils.Path;

import static org.junit.Assert.assertEquals;

public class CreateMovieTest {

    @Test
    public void insert_movie_test() throws HandlerException {
        AppConfig.setup();
        IMovieData mock = new MovieData();

        Parameters params = new Parameters();
        params.setValues("title=The+Godfather:+part+III&releaseYear=1990");
        Command cmd = new Command(Method.POST, new Path("/movies"), params);
        CreateMovieHandler handler = new CreateMovieHandler();
        handler.setMovieDataConnection(mock);
        CommandResult rs = handler.execute(cmd);

        assertEquals(1, rs.getStatus());
        for (Model test : rs) {
            Movie testMovie = (Movie) test;
            assertEquals("The Godfather: part III", testMovie.getTitle());
            assertEquals(1990, testMovie.getYear());
        }
    }

    @Test (expected = HandlerException.class)
    public void insert_duplicate_year_title_fail() throws HandlerException {
        AppConfig.setup();
        IMovieData mock = new MovieData();

        Parameters params = new Parameters();
        params.setValues("title=The+Godfather&releaseYear=1972");
        Command cmd = new Command(Method.POST, new Path("/movies"), params);
        CreateMovieHandler handler = new CreateMovieHandler();
        handler.setMovieDataConnection(mock);
        CommandResult rs = handler.execute(cmd); // expect fail here
    }

    @Test (expected = HandlerException.class)
    public void missing_parameters() throws HandlerException {
        AppConfig.setup();
        IMovieData mock = new MovieData();

        Parameters params = new Parameters();
        params.setValues("title=The+Godfather:+part+II");
        Command cmd = new Command(Method.POST, new Path("/movies"), params);
        CreateMovieHandler handler = new CreateMovieHandler();
        handler.setMovieDataConnection(mock);
        CommandResult rs = handler.execute(cmd); // expect fail here
    }
}
