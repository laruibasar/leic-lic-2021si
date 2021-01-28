package pt.isel.ls.view;

import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.Header;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PrintResults {
    public static void output(Command cmd, String output) {
        Header header = cmd.getHeader();
        String printType = header.getValue("file-name");

        if (printType.isEmpty()) {
            System.out.println(output);
            return;
        }
        filePrint(printType, output);
    }

    //TODO: a informação é acrescentada no mesmo ficheiro e não apaga a anterior
    public static void  filePrint(String fileName, String output) {
        /* validate file name */
        String validFilename = fileName
                .replaceAll("[\\\\/:*?\"<>| ]", "_");

        File file = new File(validFilename);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
            fw.write(output + "\n\n");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File " + validFilename + " created");
    }
}
