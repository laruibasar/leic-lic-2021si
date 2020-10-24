package pt.isel.ls;

import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.Parameters;

public class AppConsole {
    public static void run() {
        System.out.println("Run interactive");
        /* TODO: insert logic to interpret args and run the command:
         *  1. Ask input from user
         *  2. Interpret input
         *  2. If EXIT / terminate
         *  3. Create Command and get Handler that run the command
         *  4. Show results
         *  5. goto 1.
         */
    }

    public static void runOnce(String[] args) {
        if (args[0].toUpperCase().equals("EXIT")) {
            System.out.println("Exiting...");
        }

        Command cmd = new Command(args[0], args[1]);
        if (args.length == 3) {
            Parameters params = new Parameters(args[3]);
            cmd.setParameters(params);
        }
        System.out.println("Running command: " + cmd.toString());

        /*
         * TODO: insert logic to interpret args and run the command:
         *  1. Interpret args
         *  2. If EXIT / terminate
         *  3. Create Command and get Handler that run the command
         *  4. Show results
         *  5. Exit
         */
    }

    private void showCommand(String[] cmd) {

    }
}
