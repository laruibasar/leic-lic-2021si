package pt.isel.ls;

import pt.isel.ls.config.AppConfig;

public class App {
    public static void main(String[] args) {
        System.out.println("Application LS - G8\n");

        if (args.length == 1 || args.length > 4) {
            System.out.println("Error: Invalid number of arguments provided");
            System.out.print("Usage:\t");
            System.out.println("java pt.isel.ls.App [method path "
                    + "[parameters] [header]]");
            return;
        }

        AppConfig.setup();
        if (AppConfig.getInstance().loadConfig) {
            if (args.length == 0) {
                AppConsole.run();
            } else {
                AppConsole.runOnce(args);
            }
            return;
        } else {
            System.out.println("Error: failed to load config.");
            System.out.println(AppConfig.getInstance().loadMessage);
        }
    }
}
