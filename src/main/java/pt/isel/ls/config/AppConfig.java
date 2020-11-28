package pt.isel.ls.config;

import pt.isel.ls.handlers.*;
import pt.isel.ls.utils.Method;
import pt.isel.ls.utils.Path;

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
        this.router.addHandler(Method.POST, new Path("/users"), new CreateUserHandler());
        this.router.addHandler(Method.GET, new Path("/users"), new GetAllUsersHandler());
        this.router.addHandler(Method.GET, new Path("/users/{uid}"), new GetUserDetailsHandler());

        this.router.addHandler(Method.POST, new Path("/movies"), new CreateMovieHandler());
        this.router.addHandler(Method.GET, new Path("/movies"), new GetMoviesHandler());
        this.router.addHandler(Method.GET, new Path("/movies/{mid}"), new GetMovieDetailsHandler());

        this.router.addHandler(Method.POST, new Path("/movies/{mid}/ratings"), new RateMovieHandler());
        this.router.addHandler(Method.GET, new Path("/movies/{mid}/ratings"), new GetMovieRatingHandler());

        this.router.addHandler(Method.POST, new Path("/movies/{mid}/reviews"), new CreateMovieReviewHandler());
        this.router.addHandler(Method.GET, new Path("/movies/{mid}/reviews"), new GetMovieAllReviewsHandler());

        this.router.addHandler(Method.GET, new Path("/movies/{mid}/reviews/{rid}"), new GetMovieReviewHandler());
        this.router.addHandler(Method.GET, new Path("/users/{uid}/reviews"), new GetUserAllReviewsHandler());
        this.router.addHandler(Method.GET, new Path("/users/{uid}/reviews/{rid}"), new GetUserReviewHandler());
        this.router.addHandler(Method.GET, new Path("tops/ratings"), new GetTopRatingsHandler());

        this.router.addHandler(Method.EXIT, new Path("/"), new ExitHandler());
        this.router.addHandler(Method.OPTION, new Path("/"), new OptionHandler());

        this.router.addHandler(Method.DELETE, new Path("/movies/{mid}/review/{rid}"), new DeleteMovieReviewHandler());


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
