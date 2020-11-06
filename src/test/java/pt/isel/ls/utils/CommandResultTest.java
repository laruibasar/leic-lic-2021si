package pt.isel.ls.utils;

import org.junit.Test;
import pt.isel.ls.config.AppConfig;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.*;
import pt.isel.ls.services.*;
import pt.isel.ls.services.exceptions.InvalidAverageException;

import java.sql.SQLException;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;


public class CommandResultTest {

    public CommandResultTest() {
        AppConfig.setup();
    }


    @Test
    public void get_movie_details() throws InvalidAverageException, DataConnectionException, SQLException {
        Handler handler = new GetMovieDetailsHandler();
        Command cmd = new Command(Method.GET, new Path("/movies/1"));
        CommandResult cr = handler.execute(cmd);

        Model movie = new Movie(1, "Gladiator", 2000);
        assertEquals(movie.toString(), cr.iterator().next().toString());
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
        int n = 0;

        for (Model model : cr) {
            assertEquals(movies.get(n++).toString(), model.toString());
        }

    }

    @Test
    public void get_user_all_reviews() throws InvalidAverageException, DataConnectionException, SQLException {
        Handler handler = new GetUserAllReviewsHandler();
        Command cmd = new Command(Method.GET, new Path("/users/1/reviews"));
        CommandResult cr = handler.execute(cmd);


        Review review = new Review(1234,"Edge of Your Seat Fun!",1,5);
        assertEquals(review.toString(), cr.iterator().next().toString());

    }

    @Test
    public void get_user_details() throws InvalidAverageException, DataConnectionException, SQLException {
        Handler handler = new GetUserDetailsHandler();
        Command cmd = new Command(Method.GET, new Path("/users/1/"));
        CommandResult cr = handler.execute(cmd);

        User user = new User(1,"Mike Albuquerque","Mike-Alb@gmail.com");
        assertEquals(user.toString(), cr.iterator().next().toString());

    }

    @Test
    public void get_user_review() throws InvalidAverageException, DataConnectionException, SQLException {
        Handler handler = new GetUserReviewHandler();
        Command cmd = new Command(Method.GET, new Path("/users/1/reviews/1234"));
        CommandResult cr = handler.execute(cmd);

        Review review = new Review(1234,"Edge of Your Seat Fun!",
                "Great Story! Great Writing! Great Acting! Great Directing! This movie has it all.",
                5,1,1);
        assertEquals(review.toString(), cr.iterator().next().toString());

    }

    @Test
    public void rate_movie_handler() throws InvalidAverageException, DataConnectionException, SQLException {
        Handler handler = new RateMovieHandler();
        Parameters parameters = new Parameters();
        parameters.setValues("rating=3");
        Command cmd = new Command(Method.GET, new Path("/movies/1/ratings"),parameters);
        CommandResult cr = handler.execute(cmd);

        Rating rating = new Rating(6,3,1);
        assertEquals(rating.toString(), cr.iterator().next().toString());
        //rollback
        //Test already executed, rating already inserted

    }
}