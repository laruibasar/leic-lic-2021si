package pt.isel.ls.services;

import org.junit.Test;

import pt.isel.ls.config.AppConfig;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.services.CreateMovieHandler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Method;
import pt.isel.ls.utils.Parameters;
import pt.isel.ls.utils.Path;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

public class CreateMovieTest {

    @Test
    public void insert_correct_test() throws DataConnectionException, SQLException {
        AppConfig.setup();

        Parameters params = new Parameters();
        params.setValues("title=The+Godfather&releaseYear=1972");
        Command cmd = new Command(Method.POST, new Path("/movies"), params);
        CreateMovieHandler handler = new CreateMovieHandler();
        CommandResult rs = handler.execute(cmd);

        assertEquals(1, rs.getStatus());
        for (Model test : rs) {
            Movie testMovie = (Movie) test;
            assertEquals("The Godfather", testMovie.getTitle());
            assertEquals(1972, testMovie.getYear());
        }
    }

    @Test
    public void insert_incorrect_params_test() {

    }
}
