package scr.model.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import scr.model.service.SaveState;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveManager {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    // 1. Define the dedicated folder path
    private static final String SAVE_DIRECTORY = "saves/";

    public static void saveGame(SaveState state, int slot) {
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
            System.out.println("\n[!] Erro crítico ao salvar o arquivo: " + e.getMessage());
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
            System.out.println("\n[!] Erro ao carregar o save. Iniciando um novo jogo. (" + e.getMessage() + ")");
            return new SaveState(); 
        }
    }
}