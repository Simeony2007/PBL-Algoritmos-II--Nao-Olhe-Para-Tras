package scr.model.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import scr.model.service.SaveState;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import scr.view.GameInterface;

public class SaveManager {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // 1. Define the dedicated folder path
    public static final String SAVE_DIRECTORY = "saves/";
    private static int autoSaveCounter = 1; // ISSO RESETA QUANDO REINICIA O JOGO
    // OU SEJA, QUANDO COMEÇA O JOGO DNV, ELE SUBSTITUI OS PRIMEIROS AO INVES DE
    // CONTINUAR PELO ULTIMO

    public static void saveGame(SaveState state, int slot) {

        if (slot == -1) {
            File directory = new File(SAVE_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            if (autoSaveCounter > 5) {
                autoSaveCounter = 1;
            }
            // 3. Append the folder path to the file name
            String fileName = SAVE_DIRECTORY + "auto_save" + autoSaveCounter + ".json";
            try (FileWriter writer = new FileWriter(fileName)) {
                gson.toJson(state, writer);
                autoSaveCounter++;
            } catch (IOException e) {
                GameInterface.printWarning("\n[!] Erro crítico ao salvar o arquivo: " + e.getMessage());
            }

        } else {
            // 2. Check if the "saves" folder exists, and create it if it doesn't
            File directory = new File(SAVE_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 3. Append the folder path to the file name
            String fileName = SAVE_DIRECTORY + "save_slot_" + slot + ".json";

            try (FileWriter writer = new FileWriter(fileName)) {
                gson.toJson(state, writer);
            } catch (IOException e) {
                GameInterface.printWarning("\n[!] Erro crítico ao salvar o arquivo: " + e.getMessage());
            }
        }
    }

    public static SaveState loadGame(int slot) {
        // 4. Look for the file inside the "saves/" folder
        File file = new File(SAVE_DIRECTORY + "save_slot_" + slot + ".json");

        if (!file.exists()) {
            return new SaveState();
        }

        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, SaveState.class);
        } catch (IOException e) {
            GameInterface
                    .printWarning("\n[!] Erro ao carregar o save. Iniciando um novo jogo. (" + e.getMessage() + ")");
            return new SaveState();
        }
    }

    public static SaveState loadAutoSave(int slot) {
        // 4. Look for the file inside the "saves/" folder
        File file = new File(SAVE_DIRECTORY + "auto_save" + slot + ".json");

        if (!file.exists()) {
            return new SaveState();
        }

        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, SaveState.class);
        } catch (IOException e) {
            GameInterface
                    .printWarning("\n[!] Erro ao carregar o save. Iniciando um novo jogo. (" + e.getMessage() + ")");
            return new SaveState();
        }
    }

    public static String getSaveDateTime(int slot) {
        File file = new File(SAVE_DIRECTORY + "save_slot_" + slot + ".json");
        if (file.exists()) {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return formatador.format(new Date(file.lastModified()));
        } else {
            return null;
        }
    }

    public static String getAutoSaveDateTime(int slot) {
        File file = new File(SAVE_DIRECTORY + "auto_save" + slot + ".json");
        if (file.exists()) {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return formatador.format(new Date(file.lastModified()));
        } else {
            return null;
        }
    }
}