package pt.isel.ls.utils;

import org.junit.Test;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.MovieRating;
import pt.isel.ls.model.Review;
import pt.isel.ls.services.GetMovieDetailsHandler;
import pt.isel.ls.services.GetMovieRatingHandler;
import pt.isel.ls.services.GetTopRatingsHandler;
import pt.isel.ls.services.Handler;
import pt.isel.ls.services.exceptions.InvalidAverageException;

import java.sql.SQLException;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;


public class CommandResultTest {

    @Test
    public void get_movie_details() throws InvalidAverageException, DataConnectionException, SQLException {
//        String[] args = new String[]{"GET", "/movies/1"};
//        App.main(args);
        Handler handler = new GetMovieDetailsHandler();
        Command cmd = new Command(Method.GET, new Path("/movies/1"));
        CommandResult cr = handler.execute(cmd);

        Model movie = new Movie(1, "Gladiator", 2000);
        assertEquals(movie, cr.iterator().next());
    }

    @Test
    public void get_movie_rating() throws InvalidAverageException, DataConnectionException, SQLException {

        Handler handler = new GetMovieRatingHandler();
        Command cmd = new Command(Method.GET, new Path("/movies/1/ratings"));
        CommandResult cr = handler.execute(cmd);

        Model rating = new MovieRating(1, 4.5f, 0, 0, 0, 1, 1);

        assertEquals(cr.iterator().next(), rating);
    }

    @Test
    public void get_movie_review() throws InvalidAverageException, DataConnectionException, SQLException {
        Handler handler = new GetMovieRatingHandler();
        Command cmd = new Command(Method.GET, new Path("/movies/1/reviews/2"));
        CommandResult cr = handler.execute(cmd);

        Model review = new Review(2, "Gladiator is a historical epic from director Ridley Scott.", "Great", 1, 4, 4);

        assertEquals(cr.iterator().next(), review);
    }

    @Test
    public void get_movies() throws InvalidAverageException, DataConnectionException, SQLException {
        Handler handler = new GetMovieRatingHandler();
        Command cmd = new Command(Method.GET, new Path("/movies"));
        CommandResult cr = handler.execute(cmd);

        LinkedList<Model> movies = new LinkedList<>();
        movies.add(new Movie(1, "Gladiator", 2000));
        movies.add(new Movie(2, "The Fast and the Furious", 2001));
        movies.add(new Movie(3, "Finding Nemo", 2003));

        assertEquals(movies, cr.iterator());
    }

    @Test
    public void get_top_ratings() throws InvalidAverageException, DataConnectionException, SQLException {
        Handler handler = new GetTopRatingsHandler();
        Parameters parameters = new Parameters();
        parameters.setValues("n=10&average=highest&min=2");
        Command cmd = new Command(Method.GET, new Path("tops/ratings"),parameters);
        CommandResult cr = handler.execute(cmd);

        LinkedList<Model> movies = new LinkedList<>();
        movies.add(new Movie(1, "Gladiator", 2000));
        movies.add(new Movie(2, "The Fast and the Furious", 2001));

        assertEquals(movies, cr.iterator());
    }
}