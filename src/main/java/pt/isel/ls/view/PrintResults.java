package pt.isel.ls.view;

import pt.isel.ls.utils.Header;
import pt.isel.ls.view.results.CommandResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PrintResults {

    private final CommandResult commandResult;
    private final Header header;

    public PrintResults(CommandResult commandResult, Header header) {
        this.commandResult = commandResult;
        this.header = header;
    }

    @Override
    public String toString() {
        String printType;
        String textType;
        printType = header.getValue("file-name");
        textType = header.getValue("accept");

        if (printType.isEmpty()) {
            return consolePrint(textType);
        }

        return filePrint(printType, textType);
    }

    public String consolePrint(String textType) {
        if (textType.equals("text/html")) {
            return commandResult.printHtml();
        }
        return commandResult.printPlainText();
    }

    //TODO: a informação é acrescentada no mesmo ficheiro e não apaga a anterior
    public String filePrint(String fileName, String textType) {
        /* validate file name */
        String validFilename = fileName
                .replaceAll("[\\\\/:*?\"<>| ]", "_");

        File file = new File(validFilename);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
            if (textType.equals("text/html")) {
                fw.write(commandResult.printHtml() + "\n\n");
            } else {
                fw.write(commandResult.printPlainText() + "\n\n");
            }

            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "File " + validFilename + " created";
    }
}
