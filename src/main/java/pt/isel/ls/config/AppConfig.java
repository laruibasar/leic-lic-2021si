package pt.isel.ls.config;

import pt.isel.ls.handlers.CreateMovieHandler;
import pt.isel.ls.handlers.CreateMovieReviewHandler;
import pt.isel.ls.handlers.ExitHandler;
import pt.isel.ls.handlers.GetMovieAllReviewsHandler;
import pt.isel.ls.handlers.GetMovieDetailsHandler;
import pt.isel.ls.handlers.GetMovieRatingHandler;
import pt.isel.ls.handlers.GetMovieReviewHandler;
import pt.isel.ls.handlers.GetMoviesHandler;
import pt.isel.ls.handlers.GetTopRatingsHandler;
import pt.isel.ls.handlers.GetUserAllReviewsHandler;
import pt.isel.ls.handlers.GetUserReviewHandler;
import pt.isel.ls.handlers.OptionHandler;
import pt.isel.ls.handlers.RateMovieHandler;
import pt.isel.ls.utils.Path;
import pt.isel.ls.handlers.CreateUserHandler;
import pt.isel.ls.handlers.DeleteMovieReviewHandler;
import pt.isel.ls.handlers.GetAllUsersHandler;
import pt.isel.ls.utils.Method;
import pt.isel.ls.handlers.GetUserDetailsHandler;



/**
 * Configuration stores in memory all configurations needed to run
 * the application.
 */
public class AppConfig {
    /* store configuration load status */
    private static boolean loadConfig;
    private static String loadMessage;

    public static boolean getLoadConfig() {
        return loadConfig;
    }

    public static String getLoadMessage() {
        return loadMessage;
    }

    /* store db config */
    private static DataBaseConfig database;

    public static DataBaseConfig getDatabaseInfo() {
        return database;
    }

    /* store routing information */
    private static Router router;

    public static Router getRouter() {
        return router;
    }

    private static AppConfig config;

    public static void setup() {
        config = new AppConfig();
    }

    public static AppConfig getInstance() {
        return config;
    }

    private void loadRouter() {
        /* List all handler to load into Router */
        router.addHandler(Method.POST, new Path("/users"), new CreateUserHandler());
        router.addHandler(Method.GET, new Path("/users"), new GetAllUsersHandler());
        router.addHandler(Method.GET, new Path("/users/{uid}"), new GetUserDetailsHandler());

        router.addHandler(Method.POST, new Path("/movies"), new CreateMovieHandler());
        router.addHandler(Method.GET, new Path("/movies"), new GetMoviesHandler());
        router.addHandler(Method.GET, new Path("/movies/{mid}"), new GetMovieDetailsHandler());

        router.addHandler(Method.POST, new Path("/movies/{mid}/ratings"), new RateMovieHandler());
        router.addHandler(Method.GET, new Path("/movies/{mid}/ratings"), new GetMovieRatingHandler());

        router.addHandler(Method.POST, new Path("/movies/{mid}/reviews"), new CreateMovieReviewHandler());
        router.addHandler(Method.GET, new Path("/movies/{mid}/reviews"), new GetMovieAllReviewsHandler());

        router.addHandler(Method.GET, new Path("/movies/{mid}/reviews/{rid}"), new GetMovieReviewHandler());
        router.addHandler(Method.GET, new Path("/users/{uid}/reviews"), new GetUserAllReviewsHandler());
        router.addHandler(Method.GET, new Path("/users/{uid}/reviews/{rid}"), new GetUserReviewHandler());
        router.addHandler(Method.GET, new Path("tops/ratings"), new GetTopRatingsHandler());

        router.addHandler(Method.EXIT, new Path("/"), new ExitHandler());
        router.addHandler(Method.OPTION, new Path("/"), new OptionHandler());

        router.addHandler(Method.DELETE, new Path("/movies/{mid}/review/{rid}"), new DeleteMovieReviewHandler());
    }

    private AppConfig() {
        try {
            database = new DataBaseConfig();

            router = new Router();
            loadRouter();

            loadConfig = true;
            loadMessage = "OK";
        } catch (EnvironmentVariableException ev) {
            loadConfig = false;
            loadMessage = ev.getMessage();
        }
    }
}
