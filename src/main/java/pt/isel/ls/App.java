package pt.isel.ls;

import pt.isel.ls.config.AppConfig;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.results.ExitResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;
import pt.isel.ls.view.common.ViewRouter;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Application LS - G8\n");

        if (args.length == 1 || args.length > 4) {
            System.out.println("Error: Invalid number of arguments provided");
            System.out.print("Usage:\t");
            System.out.println("java pt.isel.ls.App [method path "
                    + "[header] [parameters]]");
            return;
        }

        AppConfig.setup();
        if (AppConfig.getLoadConfig()) {
            if (args.length == 0) {
                run();
            } else {
                runOnce(args);
            }
            return;
        } else {
            System.out.println("Error: failed to load config.");
            System.out.println(AppConfig.getLoadMessage());
        }
    }

    private static void run() {
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

    private static boolean runOnce(String[] args) {
        if (args.length == 1) {
            System.out.println("Command: method path [header] [parameters]");
            System.out.println("Run OPTION / to list available commands.\n");
            return true;
        }

        try {
            Command cmd = AppCommand.setCommand(args);

            CommandResult result = AppCommand.runCommand(cmd);
            if (result.getClass() == ExitResult.class) {
                return false;
            }

            showResults(cmd, result);
        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage() + "\n");
        }

        return true;
    }

    private static void showResults(Command cmd, CommandResult cr) throws Exception {
        ViewRouter viewRouter = AppConfig.getViewRouter();
        try {
            IView view = viewRouter.findView(cmd.getHeader(), cr);
            System.out.println(view.print(cmd, cr));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
