package pt.isel.ls.config;

import pt.isel.ls.handlers.*;
import pt.isel.ls.http.AppHttpServlet;
import pt.isel.ls.results.CreateMovieResult;
import pt.isel.ls.results.CreateMovieReviewResult;
import pt.isel.ls.results.CreateUserResult;
import pt.isel.ls.results.DeleteMovieReviewResult;
import pt.isel.ls.results.ExitResult;
import pt.isel.ls.results.GetAllUsersResult;
import pt.isel.ls.results.GetMovieAllReviewsResult;
import pt.isel.ls.results.GetMovieDetailsResult;
import pt.isel.ls.results.GetMovieRatingResult;
import pt.isel.ls.results.GetMovieReviewResult;
import pt.isel.ls.results.GetMoviesResult;
import pt.isel.ls.results.GetTopRatingResult;
import pt.isel.ls.results.GetUserDetailsResult;
import pt.isel.ls.results.GetRedirectErrorResult;
import pt.isel.ls.results.ListenResult;
import pt.isel.ls.results.OptionResult;
import pt.isel.ls.results.RootResult;
import pt.isel.ls.utils.Header;
import pt.isel.ls.utils.Path;
import pt.isel.ls.utils.Method;
import pt.isel.ls.view.common.ViewRouter;
import pt.isel.ls.view.html.CreateMovieHtmlView;
import pt.isel.ls.view.html.CreateMovieReviewHtmlView;
import pt.isel.ls.view.html.CreateUserHtmlView;
import pt.isel.ls.view.html.DeleteMovieReviewHtmlView;
import pt.isel.ls.view.html.ExitHtmlView;
import pt.isel.ls.view.html.GetAllMoviesHtmlView;
import pt.isel.ls.view.html.GetAllUsersHtmlView;
import pt.isel.ls.view.html.GetMovieAllReviewsHtmlView;
import pt.isel.ls.view.html.GetMovieDetailsHtmlView;
import pt.isel.ls.view.html.GetMovieRatingsHtmlView;
import pt.isel.ls.view.html.GetMovieReviewHtmlView;
import pt.isel.ls.view.html.GetTopsRatingsHtmlView;
import pt.isel.ls.view.html.GetUserDetailHtmlView;
import pt.isel.ls.view.html.ListenHtmlView;
import pt.isel.ls.view.html.OptionHtmlView;
import pt.isel.ls.view.html.RootHtmlView;
import pt.isel.ls.view.html.GetRedirectErrorHtmlView;
import pt.isel.ls.view.text.CreateMovieReviewTextView;
import pt.isel.ls.view.text.CreateMovieTextView;
import pt.isel.ls.view.text.CreateUserTextView;
import pt.isel.ls.view.text.DeleteMovieReviewTextView;
import pt.isel.ls.view.text.ExitTextView;
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
        router.addHandler(Method.LISTEN, new Path("/"), new ListenHandler());
        router.addHandler(Method.GET, new Path("/"), new RootHandler());

        router.addHandler(Method.GET, new Path("/redirectFailed/{error}"), new GetRedirectErrorHandler());
    }

    /* load all views into view router */
    private void loadViewsRouter() {
        viewRouter.addView(new Header("accept:text/plain"), new RootResult(), new RootTextView());
        viewRouter.addView(new Header("accept:text/html"), new RootResult(), new RootHtmlView());

        viewRouter.addView(new Header("accept:text/plain"), new CreateMovieResult(), new CreateMovieTextView());
        viewRouter.addView(new Header("accept:text/plain"), new CreateMovieResult(), new CreateMovieHtmlView());

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
        viewRouter.addView(new Header("accept:text/html"), new ListenResult(), new ListenHtmlView());

        viewRouter.addView(new Header("accept:text/plain"), new OptionResult(), new OptionTextView());
        viewRouter.addView(new Header("accept:text/html"), new OptionResult(), new OptionHtmlView());

        viewRouter.addView(new Header("accept:text/plain"), new ExitResult(), new ExitTextView());
        viewRouter.addView(new Header("accept:text/html"), new OptionResult(), new ExitHtmlView());

        viewRouter.addView(new Header("accept:text/plain"),
                new CreateMovieReviewResult(), new CreateMovieReviewTextView());
        viewRouter.addView(new Header("accept:text/html"),
                new CreateMovieReviewResult(), new CreateMovieReviewHtmlView());

        viewRouter.addView(new Header("accept:text/plain"),
                new CreateUserResult(), new CreateUserTextView());
        viewRouter.addView(new Header("accept:text/html"),
                new CreateUserResult(), new CreateUserHtmlView());

        viewRouter.addView(new Header("accept:text/plain"),
                new DeleteMovieReviewResult(), new DeleteMovieReviewTextView());
        viewRouter.addView(new Header("accept:text/html"),
                new DeleteMovieReviewResult(), new DeleteMovieReviewHtmlView());

        viewRouter.addView(new Header("accept:text/html"),
                new GetRedirectErrorResult(), new GetRedirectErrorHtmlView());

    }

    private AppConfig() {
        try {
            database = new DataBaseConfig();

            router = new Router();
            loadRouter();

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
