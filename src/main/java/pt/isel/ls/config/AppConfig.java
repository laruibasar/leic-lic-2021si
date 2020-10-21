package pt.isel.ls.config;

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

    private AppConfig() {
        try {
            database = new DataBaseConfig();
            router = new Router();

            loadConfig = true;
            loadMessage = "OK";
        } catch (EnvironmentVariableException e) {
            loadConfig = false;
            loadMessage = e.getMessage();
        } catch (RouterException e) {
            loadConfig = false;
            loadMessage = e.getMessage();
        }
    }
}
