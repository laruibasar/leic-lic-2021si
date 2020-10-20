package pt.isel.ls;

import pt.isel.ls.services.Router;
import pt.isel.ls.services.Handler;

public class AppConsole {
    public static void run() {

    }

    public static void runOnce(String[] args) {
        Handler hd = Router.getHandler(args);
        System.out.println(hd.execute());
    }
}
