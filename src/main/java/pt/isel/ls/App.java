package pt.isel.ls;

import pt.isel.ls.config.AppConfig;

public class App {
    public static void main(String[] args) {
        System.out.println("Application LS - G8");

        try {
            /*
             * Setup application
             * TODO: setup router service with handlers for application commands
             */
            AppConfig.setup();
            if (AppConfig.getInstance().loadConfig) {
                if (args.length == 0) {
                    AppConsole.run();
                } else {
                    AppConsole.runOnce(args);
                }
                System.exit(0);
            } else {
                System.out.println("Error: failed to load config\n"
                        + AppConfig.getInstance().loadMessage);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
