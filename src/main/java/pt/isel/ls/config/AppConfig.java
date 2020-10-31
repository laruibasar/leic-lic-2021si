package pt.isel.ls.config;

import pt.isel.ls.services.CreateUserHandler;
import pt.isel.ls.services.GetMoviesHandler;
import pt.isel.ls.services.GetUsersHandler;
import pt.isel.ls.services.GetRatingFromMovieHandler;
import pt.isel.ls.services.GetReviewHandler;
import pt.isel.ls.services.GetReviewFromUserHandler;
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
        this.router.addHandler(Method.GET, new Path("/users"), new GetUsersHandler());
        this.router.addHandler(Method.GET, new Path("/users/{uid}"), new GetUsersHandler());

        this.router.addHandler(Method.POST, new Path("/movies"), new GetMoviesHandler());
        this.router.addHandler(Method.GET, new Path("/movies"), new GetMoviesHandler());
        this.router.addHandler(Method.GET, new Path("/movies/{mid}"), new GetMoviesHandler());

        this.router.addHandler(Method.POST, new Path("/movies/{mid}/ratings"), new CreateReviewForMovie());
        this.router.addHandler(Method.GET, new Path("/movies/{mid}/ratings"), new GetRatingFromMovieHandler());

        this.router.addHandler(Method.POST, new Path("/movies/{mid}/reviews"), new GetReviewHandler());
        this.router.addHandler(Method.GET, new Path("/movies/{mid}/reviews"), new CreateReviewForMovie());

        this.router.addHandler(Method.GET, new Path("/movies/{mid}/reviews/{rid}"), new GetReviewFromUserHandler());
        this.router.addHandler(Method.GET, new Path("/users/{uid}/reviews"), new GetReviewFromUserHandler());
        this.router.addHandler(Method.GET, new Path("/users/{uid}/reviews/{rid}"), new GetReviewFromUserHandler());
        this.router.addHandler(Method.GET, new Path("tops/ratings"), new GetReviewFromUserHandler());
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
