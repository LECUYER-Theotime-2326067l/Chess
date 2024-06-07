package chess.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    public static void rewriteFile(String fileName, List<String> content) {
        if (!doFileExist(fileName)) createNewFile(fileName);
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            for (String line : content) {
                fileWriter.write(line);
                fileWriter.write("\n");
            }
            fileWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
            System.err.println(e.getMessage());
        }
    }

    public static List<String> readPlayers() {
        try {
            List<String> allLines = Files.readAllLines(Paths.get("players.txt"));

            for (String line : allLines) {
                System.out.println(line);
            }
            return allLines;
        } catch (IOException e) {
            createNewFile("players.txt");
//            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    public static void addPlayer(String player) {
        // if player already exists, append the number of played games
        boolean found = false;
        List<String> players = readPlayers();
        for (String p : players) {
            System.out.println(p);
            int index = players.indexOf(p);
            String[] playerData = p.split(" ");
            String playerName = playerData[0];
            if (playerName.equals(player)) {
                found = true;
                int playedGames = Integer.parseInt(playerData[1]);
                playedGames++;
                p = playerData[0] + " " + playedGames;
                players.set(index, p);
            }
        }
        if (!found) {
            players.add(player + " 1");
        }
        rewriteFile("players.txt", players);
    }

}
