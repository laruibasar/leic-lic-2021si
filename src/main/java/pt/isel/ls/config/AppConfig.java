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
import pt.isel.ls.handlers.ListenHandler;
import pt.isel.ls.handlers.OptionHandler;
import pt.isel.ls.handlers.RateMovieHandler;
import pt.isel.ls.handlers.RootHandler;
import pt.isel.ls.http.AppHttpServlet;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.Path;
import pt.isel.ls.handlers.CreateUserHandler;
import pt.isel.ls.handlers.DeleteMovieReviewHandler;
import pt.isel.ls.handlers.GetAllUsersHandler;
import pt.isel.ls.utils.Method;
import pt.isel.ls.handlers.GetUserDetailsHandler;

import java.util.ArrayList;


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

    /* store http config */
    private static HttpServletConfig httpConfig;

    public static HttpServletConfig getHttpServletConfig() {
        return httpConfig;
    }

    private static AppHttpServlet http;

    public static AppHttpServlet getHttp() {
        return http;
    }

    private static AppConfig config;

    public static void setup() {
        config = new AppConfig();
    }

    private Tree tree;
    private ArrayList<Node> nodes = new ArrayList<>();

    private void loadRouter() {
        tree = new Tree();
<<<<<<< HEAD
        nodes = new ArrayList<>(Arrays.asList(

                //root
                new Node(new Command(Method.LISTEN, new Path("/")), new ListenHandler()),

                new Node(new Command(Method.GET, new Path("/users")), new GetAllUsersHandler()),
                new Node(new Command(Method.POST, new Path("/users")), new CreateUserHandler()),
                new Node(new Command(Method.DELETE, new Path("/movies/{mid}/review/{rid}")),
                        new DeleteMovieReviewHandler()),
                new Node(new Command(Method.OPTION, new Path("/")), new OptionHandler()),
                new Node(new Command(Method.EXIT, new Path("/")), new ExitHandler()),


                new Node(new Command(Method.GET, new Path("/users/{uid}")), new GetUserDetailsHandler()),
                new Node(new Command(Method.GET, new Path("/movies")), new GetMoviesHandler()),
                new Node(new Command(Method.GET, new Path("/movies/{mid}")), new GetMovieDetailsHandler()),
                new Node(new Command(Method.GET, new Path("/movies/{mid}/ratings")), new GetMovieRatingHandler()),
                new Node(new Command(Method.GET, new Path("/movies/{mid}/reviews")), new GetMovieAllReviewsHandler()),
                new Node(new Command(Method.GET, new Path("/movies/{mid}/reviews/{rid}")), new GetMovieReviewHandler()),
                new Node(new Command(Method.GET, new Path("/users/{uid}/reviews")), new GetUserAllReviewsHandler()),
                new Node(new Command(Method.GET, new Path("/users/{uid}/reviews/{rid}")), new GetUserReviewHandler()),
                new Node(new Command(Method.GET, new Path("tops/ratings")), new GetTopRatingsHandler()),

                new Node(new Command(Method.POST, new Path("/users")), new CreateUserHandler()),
                new Node(new Command(Method.POST, new Path("/movies")), new CreateMovieHandler()),
                new Node(new Command(Method.POST, new Path("/movies/{mid}/ratings")), new RateMovieHandler()),
                new Node(new Command(Method.POST, new Path("/movies/{mid}/reviews")), new CreateMovieReviewHandler()),

                new Node(new Command(Method.DELETE, new Path("/movies/{mid}/review/{rid}")), new DeleteMovieReviewHandler())
        ));
        tree.buildTree(nodes, 6);
=======
        nodes.add(new Node(new Command(Method.POST, new Path("/users")), new CreateUserHandler()));
        nodes.add(new Node(new Command(Method.POST, new Path("/users")), new CreateUserHandler()));
        nodes.add(new Node(new Command(Method.GET, new Path("/users")), new GetAllUsersHandler()));

        nodes.add(new Node(new Command(Method.POST, new Path("/movies")), new CreateMovieHandler()));
        nodes.add(new Node(new Command(Method.POST, new Path("/movies/{mid}/ratings")), new RateMovieHandler()));

        nodes.add(new Node(new Command(Method.GET, new Path("/users/{uid}")), new GetUserDetailsHandler()));
        nodes.add(new Node(new Command(Method.GET, new Path("/movies")), new GetMoviesHandler()));

        nodes.add(new Node(new Command(Method.POST, new Path("/movies/{mid}/reviews")), new CreateMovieReviewHandler()));
        nodes.add(new Node(new Command(Method.EXIT, new Path("/")), new ExitHandler()));
        nodes.add(new Node(new Command(Method.OPTION, new Path("/")), new OptionHandler()));
        nodes.add(new Node(new Command(Method.DELETE, new Path("/movies/{mid}/review/{rid}")), new DeleteMovieReviewHandler()));

        nodes.add(new Node(new Command(Method.GET, new Path("/movies/{mid}")), new GetMovieDetailsHandler()));
        nodes.add(new Node(new Command(Method.GET, new Path("/movies/{mid}/ratings")), new GetMovieRatingHandler()));
        nodes.add(new Node(new Command(Method.GET, new Path("/movies/{mid}/reviews")), new GetMovieAllReviewsHandler()));
        nodes.add(new Node(new Command(Method.GET, new Path("/movies/{mid}/reviews/{rid}")), new GetMovieReviewHandler()));

        nodes.add(new Node(new Command(Method.GET, new Path("/users/{uid}/reviews")), new GetUserAllReviewsHandler()));
        nodes.add(new Node(new Command(Method.GET, new Path("/users/{uid}/reviews/{rid}")), new GetUserReviewHandler()));
        nodes.add(new Node(new Command(Method.GET, new Path("tops/ratings")), new GetTopRatingsHandler()));

        nodes.add(new Node(new Command(Method.EXIT, new Path("/")), new ExitHandler()));
        nodes.add(new Node(new Command(Method.OPTION, new Path("/")), new OptionHandler()));

        nodes.add(new Node(new Command(Method.LISTEN, new Path("/")), new ListenHandler()));
        nodes.add(new Node(new Command(Method.GET, new Path("/")), new RootHandler()));
        tree.buildTree(nodes);
>>>>>>> 70d472368bface5f825e642076d5205a4c192c7d



    }

    private AppConfig() {
        try {
            database = new DataBaseConfig();
            loadRouter();
            router = new Router(tree);

            httpConfig = new HttpServletConfig();
            http = new AppHttpServlet();

            loadConfig = true;
            loadMessage = "OK";
        } catch (EnvironmentVariableException ev) {
            loadConfig = false;
            loadMessage = ev.getMessage();
        }
    }
}
