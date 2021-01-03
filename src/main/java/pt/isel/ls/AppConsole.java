package pt.isel.ls;

import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.results.ExitResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.config.RouterException;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Header;
import pt.isel.ls.view.PrintResults;

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
        } catch (RouterException | HandlerException e) {
            System.out.println("ERROR " + e.getMessage() + "\n");
        }

        return true;
    }

    private static void showResults(Command cmd, CommandResult cr) {
        Header hd = cmd.getHeader();
        System.out.println(new PrintResults(cr, hd).toString());
    }
}
