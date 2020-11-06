package pt.isel.ls;

import pt.isel.ls.model.Model;
import pt.isel.ls.services.Handler;
import pt.isel.ls.services.exceptions.InvalidAverageException;
import pt.isel.ls.utils.*;
import pt.isel.ls.config.AppConfig;
import pt.isel.ls.config.RouterException;
import pt.isel.ls.data.DataConnectionException;

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
            } catch (Exception e) {
                System.out.println("ERROR " + e.getMessage() + "\n");
            }
        }
    }

    private static String readInput() {
        System.out.println("Enter command to run: ");
        System.out.print("> ");
        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    public static boolean runOnce(String[] args) {

        if (args[0].toUpperCase().equals("EXIT")) {
            System.out.println("Exiting...");
            return false;
        }

        Command cmd = setCommand(args);
        System.out.println("Running command: " + cmd.toString());

        try {
            CommandResult result = runCommand(cmd);
            showResults(result);
        } catch (RouterException | DataConnectionException | SQLException | InvalidAverageException | ParametersExceptions e) {
            System.out.println("ERROR " + e.getMessage() + "\n");
        }

        return true;
    }

    private static Command setCommand(String[] args) {
        Method method = Method.getMethod(args[0]);
        Path path = new Path(args[1]);
        Command cmd = new Command(method, path);
        if (args.length == 3) {
            Parameters params = new Parameters();
            params.setValues(args[2]);
            cmd.setParameters(params);
        }

        return cmd;
    }

    private static CommandResult runCommand(Command cmd) throws RouterException,
            DataConnectionException, SQLException, InvalidAverageException, ParametersExceptions {

        Handler handler = AppConfig.getInstance().router.findHandler(cmd);
        return handler.execute(cmd);
    }

    private static void showResults(CommandResult cr) {
        for (Model model : cr) {
            System.out.println(model.toString());
        }
    }
}
