package pt.isel.ls;

import pt.isel.ls.config.AppConfig;
import pt.isel.ls.config.RouterException;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.services.Handler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;

import java.sql.SQLException;
import java.util.Scanner;

public class AppConsole {
    public static void run() {
        System.out.println("Run in interactive mode");

        boolean run = true;
        while (run) {
            String input = readInput();
            String[] args = input.split(" ");

            try {
                run = runOnce(args);
            } catch (RouterException | DataConnectionException | SQLException re) {
                System.out.println(re.getMessage());
            }
        }
    }

    private static String readInput() {
        System.out.println("Enter command to run: ");
        System.out.print("> ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();

        return input;
    }

    public static boolean runOnce(String[] args) throws RouterException, DataConnectionException, SQLException {
        if (args[0].toUpperCase().equals("EXIT")) {
            System.out.println("Exiting...");
            return false;
        }

        Command cmd = setCommand(args);
        System.out.println("Running command: " + cmd.toString());

        CommandResult result = runCommand(cmd);
        showResults(result);

        return true;
    }

    private static Command setCommand(String[] args) {
        Command cmd = new Command(args[0], args[1]);
        if (args.length == 3) {
            Parameters params = new Parameters();
            params.setValues(args[2]);
            cmd.setParameters(params);
        }

        return cmd;
    }

    private static CommandResult runCommand(Command cmd) throws RouterException, DataConnectionException, SQLException {
        Handler handler = AppConfig.getInstance().router.findHandler(cmd);
        CommandResult result = handler.execute(cmd);

        return result;
    }

    private static void showResults(CommandResult result) {
        // TODO: pretty print results
    }
}
