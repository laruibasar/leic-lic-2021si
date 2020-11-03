package pt.isel.ls.config;

import pt.isel.ls.services.*;
import pt.isel.ls.utils.Method;
import pt.isel.ls.utils.Path;

/**
 * Configuration stores in memory all configurations needed to run
 * the application.
 */
public class AppConfig {
    /* store configuration load status */
    public boolean loadConfig;
    public String loadMessage;

    /* store db config */
    public DataBaseConfig database;

    /* store routing information */
    public Router router;

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
        this.router.addHandler(Method.GET, new Path("/movies/{mid}/reviews"), new GetMovieReviewsHandler());

        this.router.addHandler(Method.GET, new Path("/movies/{mid}/reviews/{rid}"), new GetMovieRatingInformationHandler());
        this.router.addHandler(Method.GET, new Path("/users/{uid}/reviews"), new GetUserReviewsHandler());
        this.router.addHandler(Method.GET, new Path("/users/{uid}/reviews/{rid}"), new GetUserReviewHandler());
        this.router.addHandler(Method.GET, new Path("tops/ratings"), new GetTopRatingsHandler());
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
