package pt.isel.ls.utils;

import org.junit.Test;
import pt.isel.ls.config.AppConfig;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.mockdata.MockDataTransaction;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.MovieRating;
import pt.isel.ls.model.Rating;
import pt.isel.ls.model.Review;
import pt.isel.ls.model.User;
import pt.isel.ls.handlers.GetMovieDetailsHandler;
import pt.isel.ls.handlers.GetMovieRatingHandler;
import pt.isel.ls.handlers.GetMovieReviewHandler;
import pt.isel.ls.handlers.GetMoviesHandler;
import pt.isel.ls.handlers.GetTopRatingsHandler;
import pt.isel.ls.handlers.GetUserAllReviewsHandler;
import pt.isel.ls.handlers.GetUserDetailsHandler;
import pt.isel.ls.handlers.GetUserReviewHandler;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.RateMovieHandler;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;


public class CommandResultTest {

    public CommandResultTest() {
        AppConfig.setup();
    }


    @Test
    public void get_movie_details() throws HandlerException {
        Handler handler = new GetMovieDetailsHandler();
        Command cmd = new Command(Method.GET, new Path("/movies/1"));

        // Setup template to get mid out
        cmd.setTemplate(new Command(Method.GET, new Path("/movies/{mid}")));

        CommandResult cr = handler.execute(cmd);

        Model movie = new Movie(1, "Gladiator", 2000);
        assertEquals(movie.toString(), cr.iterator().next().toString());
    }

    @Test
    public void get_movie_rating() throws HandlerException {

        Handler handler = new GetMovieRatingHandler();
        Command cmd = new Command(Method.GET, new Path("/movies/1/ratings"));

        // Setup template to get mid out
        cmd.setTemplate(new Command(Method.GET, new Path("/movies/{mid}/ratings")));

        CommandResult cr = handler.execute(cmd);

        Model rating = new MovieRating(1, 4.5f, 0, 0, 0, 2, 2);

        assertEquals(rating.toString(), cr.iterator().next().toString());
    }

    @Test
    public void get_movie_review() throws HandlerException {
        Handler handler = new GetMovieReviewHandler();
        Command cmd = new Command(Method.GET, new Path("/movies/1/reviews/2"));
        CommandResult cr = handler.execute(cmd);

        Model review = new Review(2, "Great",
                "Gladiator is a historical epic from director Ridley Scott.", 4, 1, 4);

        assertEquals(review.toString(), cr.iterator().next().toString());
    }

    @Test
    public void get_movies() throws HandlerException {
        Handler handler = new GetMoviesHandler();
        Command cmd = new Command(Method.GET, new Path("/movies"));

        LinkedList<Model> movies = new LinkedList<>();
        movies.add(new Movie(1, "Gladiator", 2000));
        movies.add(new Movie(2, "The Fast and the Furious", 2001));
        movies.add(new Movie(3, "Finding Nemo", 2003));
        int n = 0;
        CommandResult cr = handler.execute(cmd);
        for (Model model : cr) {
            assertEquals(movies.get(n++).toString(), model.toString());
            if (n == 3) {
                return;
            }
        }
    }

    @Test
    public void get_top_ratings() throws HandlerException {
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
            if (n == 2) {
                return;
            }
        }

    }

    @Test
    public void get_user_all_reviews() throws HandlerException {
        Handler handler = new GetUserAllReviewsHandler();
        Command cmd = new Command(Method.GET, new Path("/users/1/reviews"));

        // Setup template to get mid out
        cmd.setTemplate(new Command(Method.GET, new Path("/users/{uid}/reviews")));

        CommandResult cr = handler.execute(cmd);

        Review review = new Review("Edge of Your Seat Fun!",
                "Great Story! Great Writing! Great Acting! Great Directing! This movie has it all.",
                1,5, 1);
        review.setId(1);

        assertEquals(review.toString(), cr.iterator().next().toString());

    }

    @Test
    public void get_user_details() throws HandlerException {
        Handler handler = new GetUserDetailsHandler();
        Command cmd = new Command(Method.GET, new Path("/users/1/"));

        // Setup template to get mid out
        cmd.setTemplate(new Command(Method.GET, new Path("/users/{uid}")));

        CommandResult cr = handler.execute(cmd);

        User user = new User(1,"Mike Albuquerque","Mike-Alb@gmail.com");
        assertEquals(user.toString(), cr.iterator().next().toString());

    }

    @Test
    public void get_user_review() throws DataConnectionException, HandlerException {
        Handler handler = new GetUserReviewHandler();
        Command cmd = new Command(Method.GET, new Path("/users/1/reviews/1234"));

        // Setup template to get mid out
        cmd.setTemplate(new Command(Method.GET, new Path("/users/{uid}/reviews/{rid}")));

        CommandResult cr = handler.execute(cmd);

        Review review = new Review(1234,"Edge of Your Seat Fun!",
                "Great Story! Great Writing!"
                        + " Great Acting! Great Directing! This movie has it all.",
                5,1,1);
        assertEquals(review.toString(), cr.iterator().next().toString());

    }

    @Test
    public void rate_movie_handler() throws HandlerException {
        Handler handler = new RateMovieHandler();
        Parameters parameters = new Parameters();
        parameters.setValues("rating=3");
        Command cmd = new Command(Method.POST, new Path("/movies/1/ratings"),parameters);

        // Setup template to get mid out
        cmd.setTemplate(new Command(Method.POST, new Path("/movies/{mid}/ratings")));
        handler.setDataTransaction(new MockDataTransaction());

        CommandResult cr = handler.execute(cmd);

        Rating rating = new Rating(6,3,1);
        assertEquals(rating.toString(), cr.iterator().next().toString());
        //rollback
        //Test already executed, rating already inserted

    }
}