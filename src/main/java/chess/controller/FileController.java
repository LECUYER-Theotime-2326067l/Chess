package chess.controller;

import java.io.File;
import java.io.FileWriter;

public class FileController {

    public static void createNewFile(String fileName) {
        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            System.err.println(e.getMessage());
        }
    }

    public static boolean doFileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public static void writeToFile(String fileName, String content) {
        if (!doFileExist(fileName)) createNewFile(fileName);
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(content);
            fileWriter.write("\n");
            fileWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
            System.err.println(e.getMessage());
        }
    }

}
