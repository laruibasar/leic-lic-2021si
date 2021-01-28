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
import pt.isel.ls.results.CreateMovieResult;
import pt.isel.ls.results.GetAllUsersResult;
import pt.isel.ls.results.GetMovieAllReviewsResult;
import pt.isel.ls.results.GetMovieDetailsResult;
import pt.isel.ls.results.GetMovieRatingResult;
import pt.isel.ls.results.GetMovieReviewResult;
import pt.isel.ls.results.GetMoviesResult;
import pt.isel.ls.results.GetTopRatingResult;
import pt.isel.ls.results.GetUserDetailsResult;
import pt.isel.ls.results.ListenResult;
import pt.isel.ls.results.OptionResult;
import pt.isel.ls.results.RootResult;
import pt.isel.ls.utils.Header;
import pt.isel.ls.utils.Path;
import pt.isel.ls.handlers.CreateUserHandler;
import pt.isel.ls.handlers.DeleteMovieReviewHandler;
import pt.isel.ls.handlers.GetAllUsersHandler;
import pt.isel.ls.utils.Method;
import pt.isel.ls.handlers.GetUserDetailsHandler;

import java.util.ArrayList;
import java.util.Arrays;
import pt.isel.ls.view.common.ViewRouter;
import pt.isel.ls.view.html.GetAllMoviesHtmlView;
import pt.isel.ls.view.html.GetAllUsersHtmlView;
import pt.isel.ls.view.html.GetMovieAllReviewsHtmlView;
import pt.isel.ls.view.html.GetMovieDetailsHtmlView;
import pt.isel.ls.view.html.GetMovieRatingsHtmlView;
import pt.isel.ls.view.html.GetMovieReviewHtmlView;
import pt.isel.ls.view.html.GetTopsRatingsHtmlView;
import pt.isel.ls.view.html.GetUserDetailHtmlView;
import pt.isel.ls.view.html.OptionHtmlView;
import pt.isel.ls.view.html.RootHtmlView;
import pt.isel.ls.view.text.CreateMovieTextView;
import pt.isel.ls.view.text.GetAllMoviesTextView;
import pt.isel.ls.view.text.GetAllUsersTextView;
import pt.isel.ls.view.text.GetMovieAllReviewsTextView;
import pt.isel.ls.view.text.GetMovieDetailsTextView;
import pt.isel.ls.view.text.GetMovieRatingsTextView;
import pt.isel.ls.view.text.GetMovieReviewTextView;
import pt.isel.ls.view.text.GetTopsRatingsTextView;
import pt.isel.ls.view.text.GetUserDetailTextView;
import pt.isel.ls.view.text.ListenTextView;
import pt.isel.ls.view.text.OptionTextView;
import pt.isel.ls.view.text.RootTextView;


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

    /* check for remote deploy environment
    * this will allow to run only the servlet in endless loop
    * until Heroku terminates the app when:
    *  - no connections
    *  - on git push
    *  - there is a way with processes and workers?
    *
    * set environment variable REMOTE = 1 ou HEROKU
    */
    private static boolean remoteDeploy = false;

    public static boolean isRemoteDeploy() {
        return remoteDeploy;
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

    private static ViewRouter viewRouter;

    public static ViewRouter getViewRouter() {
        return viewRouter;
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
    private ArrayList<Node> nodes;

    private void loadRouter() {
        tree = new Tree();
        nodes = new ArrayList<>(Arrays.asList(

                //tree root
                new Node(new Command(Method.LISTEN, new Path("/")), new ListenHandler()),

                //children of the root
                new Node(new Command(Method.GET, new Path("/users")), new GetAllUsersHandler()),
                new Node(new Command(Method.POST, new Path("/users")), new CreateUserHandler()),
                new Node(new Command(Method.DELETE, new Path("/movies/{mid}/review/{rid}")),
                        new DeleteMovieReviewHandler()),
                new Node(new Command(Method.OPTION, new Path("/")), new OptionHandler()),
                new Node(new Command(Method.EXIT, new Path("/")), new ExitHandler()),

                //sub trees of the root children's
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
    }

    /* load all views into view router */
    private void loadViewsRouter() {
        viewRouter.addView(new Header("accept:text/plain"), new RootResult(), new RootTextView());
        viewRouter.addView(new Header("accept:text/html"), new RootResult(), new RootHtmlView());

        viewRouter.addView(new Header("accept:text/plain"), new CreateMovieResult(), new CreateMovieTextView());

        viewRouter.addView(new Header("accept:text/plain"), new GetAllUsersResult(), new GetAllUsersTextView());
        viewRouter.addView(new Header("accept:text/html"), new GetAllUsersResult(), new GetAllUsersHtmlView());

        viewRouter.addView(new Header("accept:text/plain"), new GetUserDetailsResult(), new GetUserDetailTextView());
        viewRouter.addView(new Header("accept:text/html"), new GetUserDetailsResult(), new GetUserDetailHtmlView());

        viewRouter.addView(new Header("accept:text/plain"), new GetMoviesResult(), new GetAllMoviesTextView());
        viewRouter.addView(new Header("accept:text/html"), new GetMoviesResult(), new GetAllMoviesHtmlView());

        viewRouter.addView(new Header("accept:text/plain"), new GetMovieDetailsResult(), new GetMovieDetailsTextView());
        viewRouter.addView(new Header("accept:text/html"), new GetMovieDetailsResult(), new GetMovieDetailsHtmlView());

        viewRouter.addView(new Header("accept:text/plain"), new GetMovieReviewResult(), new GetMovieReviewTextView());
        viewRouter.addView(new Header("accept:text/html"), new GetMovieReviewResult(), new GetMovieReviewHtmlView());

        viewRouter.addView(new Header("accept:text/plain"),
                new GetMovieAllReviewsResult(), new GetMovieAllReviewsTextView());
        viewRouter.addView(new Header("accept:text/html"),
                new GetMovieAllReviewsResult(), new GetMovieAllReviewsHtmlView());

        viewRouter.addView(new Header("accept:text/plain"), new GetMovieRatingResult(), new GetMovieRatingsTextView());
        viewRouter.addView(new Header("accept:text/html"), new GetMovieRatingResult(), new GetMovieRatingsHtmlView());

        viewRouter.addView(new Header("accept:text/plain"), new GetTopRatingResult(), new GetTopsRatingsTextView());
        viewRouter.addView(new Header("accept:text/html"), new GetTopRatingResult(), new GetTopsRatingsHtmlView());

        viewRouter.addView(new Header("accept:text/plain"), new ListenResult(), new ListenTextView());

        viewRouter.addView(new Header("accept:text/plain"), new OptionResult(), new OptionTextView());
        viewRouter.addView(new Header("accept:text/html"), new OptionResult(), new OptionHtmlView());
    }

    private AppConfig() {
        try {
            database = new DataBaseConfig();
            loadRouter();
            router = new Router(tree);

            viewRouter = new ViewRouter();
            loadViewsRouter();

            httpConfig = new HttpServletConfig();
            http = new AppHttpServlet();

            String remote = System.getenv("REMOTE");
            if (remote != null && remote.length() > 0) {
                remoteDeploy = true;
            }

            loadConfig = true;
            loadMessage = "OK";
        } catch (EnvironmentVariableException ev) {
            loadConfig = false;
            loadMessage = ev.getMessage();
        }
    }
}
