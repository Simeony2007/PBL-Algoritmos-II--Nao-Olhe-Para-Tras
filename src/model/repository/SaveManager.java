package src.model.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import src.model.service.SaveState;
import src.model.service.Settings;
import src.model.service.Collection;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class SaveManager {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // 1. Define the dedicated folder path
    public static final String SAVE_DIRECTORY = "data/saves/";
    public static final String PROFILE_DIRECTORY = "data/profile/";

    // Saves
    public static void saveGame(SaveState state, int slot) {
        Settings settings = loadSettings();

        if (slot == -1) {
            File directory = new File(SAVE_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (settings.isAutoSave() && settings.getAutoSaveCounter() > 5) {
                settings.addAutoSaveCounter();
            }
            // 3. Append the folder path to the file name
            String fileName = SAVE_DIRECTORY + "auto_save_" + settings.getAutoSaveCounter() + ".json";
            try (FileWriter writer = new FileWriter(fileName)) {
                gson.toJson(state, writer);
                settings.addAutoSaveCounter();
                SaveManager.saveSettings(settings);
            } catch (IOException e) {
                // GameInterface.printWarning("\n[!] Erro crítico ao salvar o arquivo: " +
                // e.getMessage());
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
                // GameInterface.printWarning("\n[!] Erro crítico ao salvar o arquivo: " +
                // e.getMessage());
            }
        }
    }

    public static SaveState loadGame(int slot) {
        File file;
        if (slot > 0 && slot <= 3) {
            file = new File(SAVE_DIRECTORY + "save_slot_" + slot + ".json");
        } else {
            slot = slot - 3;
            file = new File(SAVE_DIRECTORY + "auto_save_" + slot + ".json");
        }

        if (!file.exists()) {
            return new SaveState();
        }

        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, SaveState.class);
        } catch (IOException e) {
            // GameInterface.printWarning("\n[!] Erro ao carregar o save. Iniciando um novo
            // jogo. (" + e.getMessage() + ")");
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
        File file = new File(SAVE_DIRECTORY + "auto_save_" + slot + ".json");
        if (file.exists()) {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return formatador.format(new Date(file.lastModified()));
        } else {
            return null;
        }
    }

    public static void deleteSave(int saveSlot) {
        if (saveSlot > 3) {
            File file = new File(SAVE_DIRECTORY + "auto_save_" + saveSlot + ".json");
            if (file.exists()) {
                file.delete();
            } else {
                // throw CUSTOMEXCEPTION (DÁ PRA COLOCAR UMA AQ!!)
            }
        } else {
            File file = new File(SAVE_DIRECTORY + "save_slot_" + saveSlot + ".json");
            if (file.exists()) {
                file.delete();
            } else {
                // throw CUSTOMEXCEPTION (DÁ PRA COLOCAR UMA AQ!!)
            }
        }
    }

    public static boolean doesSaveExist(int saveSlot) {
        File file = new File(SAVE_DIRECTORY + "save_slot_" + saveSlot + ".json");
        return file.exists();
    }

    // Settings
    public static void saveSettings(Settings currentSettings) {
        File directory = new File(PROFILE_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = PROFILE_DIRECTORY + "settings.json";
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(currentSettings, writer);
        } catch (IOException e) {
            // GameInterface.printWarning("\n[!] Erro crítico ao salvar o arquivo: " +
            // e.getMessage());
        }

    }

    public static Settings loadSettings() {
        File file = new File(PROFILE_DIRECTORY + "settings.json");
        if (!file.exists()) {
            return new Settings();
        } else {
            try (FileReader reader = new FileReader(file)) {
                return gson.fromJson(reader, Settings.class);
            } catch (IOException e) {
                // GameInterface.printWarning("\n[!] Erro ao carregar. Reiniciando
                // settingsurações. (" + e.getMessage() + ")");
                return new Settings();
            }
        }
    }

    // Collection
    public static void saveCollection(Collection currentCollection) {
        File directory = new File(PROFILE_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = PROFILE_DIRECTORY + "collection.json";
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(currentCollection, writer);
        } catch (IOException e) {
            // GameInterface.printWarning("\n[!] Erro crítico ao salvar o arquivo: " +
            // e.getMessage());
        }

    }

    public static Collection loadCollection() {
        File file = new File(PROFILE_DIRECTORY + "collection.json");
        if (!file.exists()) {
            return new Collection();
        } else {
            try (FileReader reader = new FileReader(file)) {
                return gson.fromJson(reader, Collection.class);
            } catch (IOException e) {
                // GameInterface.printWarning("\n[!] Erro ao carregar. Reiniciando
                // settingsurações. (" + e.getMessage() + ")");
                return new Collection();
            }
        }
    }
}