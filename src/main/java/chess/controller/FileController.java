package chess.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestionnaire de fichiers externes
 */
public class FileController {

    /**
     * Crée un nouveau fichier
     * @param fileName nom du fichier à créer
     */
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

    /**
     * Vérifie si un fichier existe
     * @param fileName nom du fichier à vérifier
     * @return true si le fichier existe, false sinon
     */
    public static boolean doFileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    /**
     * Ajoute une chaîne de caractères dans un fichier
     * @param fileName nom du fichier
     * @param content contenu à ajouter
     */
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

    /**
     * Réécrit un fichier avec une liste de chaînes de caractères
     * @param fileName nom du fichier
     * @param content contenu à réécrire
     */
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

    /**
     * Lit le fichier des joueurs et retourne une liste de joueurs avec leur nombre de parties jouées
     * @return liste des joueurs
     */
    public static List<String> readPlayers() {
        try {
            return Files.readAllLines(Paths.get("players.txt"));
        } catch (IOException e) {
            createNewFile("players.txt");
        }
        return new ArrayList<>();
    }

    /**
     * Ajoute un joueur dans le fichier des joueurs
     * Si le joueur existe déjà, incrémente le nombre de parties jouées
     * @param player nom du joueur
     */
    public static void addPlayer(String player) {
        // Si le joueur existe déjà, incrémente le nombre de parties jouées
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
