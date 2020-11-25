package pt.isel.ls;

import pt.isel.ls.model.Model;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.config.AppConfig;
import pt.isel.ls.config.RouterException;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Method;
import pt.isel.ls.utils.Parameters;
import pt.isel.ls.utils.Path;

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
        try {
            Command cmd = setCommand(args);
            System.out.println("Running command: " + cmd.toString());

            /* temporary fix, later we use a special CommandResult */
            if (cmd.getMethod() == Method.EXIT) {
                return false;
            }

            CommandResult result = runCommand(cmd);
            showResults(result);
        } catch (RouterException | DataConnectionException | HandlerException e) {
            System.out.println("ERROR " + e.getMessage() + "\n");
        }

        return true;
    }

    private static Command setCommand(String[] args) throws RouterException {
        Method method = Method.getMethod(args[0].toUpperCase());
        Path path = new Path(args[1]);
        Parameters params = new Parameters();
        if (args.length == 3) {
            params.setValues(args[2]);
        }
        Command cmd = new Command(method, path, params);

        try {
            cmd.setTemplate(AppConfig.getRouter().findTemplate(cmd));
        } catch (RouterException e) {
            throw new RouterException(e.getMessage());
        }

        return cmd;
    }

    private static CommandResult runCommand(Command cmd) throws RouterException,
            DataConnectionException, HandlerException {

        Handler handler = AppConfig.getRouter().findHandler(cmd);
        return handler.execute(cmd);
    }

    private static void showResults(CommandResult cr) {
        for (Model model : cr) {
            System.out.println(model.toString());
        }
    }
}
