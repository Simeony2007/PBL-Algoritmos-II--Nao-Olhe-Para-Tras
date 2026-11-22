package src.model.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import src.model.service.SaveState;
import src.model.service.Settings;
import src.model.Observer;
import src.model.customExceptions.SaveNotFoundException;
import src.model.service.Collection;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SaveManager {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static ArrayList<Observer> observers = new ArrayList<>();

    // 1. Define the dedicated folder path
    public static final String SAVE_DIRECTORY = "data/saves/";
    public static final String PROFILE_DIRECTORY = "data/profile/";

    private static File saveDirectory() {
        return new File(System.getProperty("pbl.data.directory", "data"), "saves");
    }

    private static File profileDirectory() {
        return new File(System.getProperty("pbl.data.directory", "data"), "profile");
    }

    private static File saveFile(String fileName) {
        return new File(saveDirectory(), fileName);
    }

    private static File profileFile(String fileName) {
        return new File(profileDirectory(), fileName);
    }

    // Saves
    public static void saveGame(SaveState state, int slot) {
        Settings settings = loadSettings();

        if (slot == -1) {
            File directory = saveDirectory();
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (settings.isAutoSave() && settings.getAutoSaveCounter() > 5) {
                settings.setAutoSaveInOne();
            }
            // 3. Append the folder path to the file name
            File file = saveFile("auto_save_" + settings.getAutoSaveCounter() + ".json");
            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(state, writer);
                settings.addAutoSaveCounter();
                SaveManager.saveSettings(settings);
                notifyObserversSaveMade();
            } catch (IOException e) {
                // GameInterface.printWarning("\n[!] Erro crítico ao salvar o arquivo: " +
                // e.getMessage());
            }

        } else {
            // 2. Check if the "saves" folder exists, and create it if it doesn't
            File directory = saveDirectory();
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 3. Append the folder path to the file name
            File file = saveFile("save_slot_" + slot + ".json");

            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(state, writer);
                notifyObserversSaveMade();
            } catch (IOException e) {
                // GameInterface.printWarning("\n[!] Erro crítico ao salvar o arquivo: " +
                // e.getMessage());
            }
        }
    }

    public static SaveState loadGame(int slot) {
        File file;
        if (slot > 0 && slot <= 3) {
            file = saveFile("save_slot_" + slot + ".json");
        } else {
            slot = slot - 3;
            file = saveFile("auto_save_" + slot + ".json");
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
        File file = saveFile("save_slot_" + slot + ".json");

        if (file.exists()) {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return formatador.format(new Date(file.lastModified()));
        } else {
            return null;
        }
    }

    public static String getAutoSaveDateTime(int slot) {
        File file = saveFile("auto_save_" + slot + ".json");
        if (file.exists()) {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return formatador.format(new Date(file.lastModified()));
        } else {
            return null;
        }
    }

    public static void deleteSave(int saveSlot) throws SaveNotFoundException {
        if (saveSlot > 3) {
            File file = saveFile("auto_save_" + (saveSlot - 3) + ".json");
            if (file.exists()) {
                file.delete();
                notifyObserversSaveDeleted();
            } else {
                // throw CUSTOMEXCEPTION (DÁ PRA COLOCAR UMA AQ!!)
                throw new SaveNotFoundException(saveSlot);
            }
        } else {
            File file = saveFile("save_slot_" + saveSlot + ".json");
            if (file.exists()) {
                file.delete();
                notifyObserversSaveDeleted();
            } else {
                // throw CUSTOMEXCEPTION (DÁ PRA COLOCAR UMA AQ!!)
                throw new SaveNotFoundException(saveSlot);
            }
        }
    }

    public static boolean doesSaveExist(int saveSlot) {
        File file = saveFile("save_slot_" + saveSlot + ".json");
        return file.exists();
    }

    // Settings
    public static void saveSettings(Settings currentSettings) {
        File directory = profileDirectory();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = profileFile("settings.json");
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(currentSettings, writer);
        } catch (IOException e) {
            // GameInterface.printWarning("\n[!] Erro crítico ao salvar o arquivo: " +
            // e.getMessage());
        }

    }

    public static Settings loadSettings() {
        File file = profileFile("settings.json");
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
        File directory = profileDirectory();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = profileFile("collection.json");
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(currentCollection, writer);
        } catch (IOException e) {
            // GameInterface.printWarning("\n[!] Erro crítico ao salvar o arquivo: " +
            // e.getMessage());
        }

    }

    public static Collection loadCollection() {
        File file = profileFile("collection.json");
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

    public static void notifyObserversSaveMade() {
        for (Observer obs : observers) {
            obs.saveWasMade();
        }
    }

    public static void notifyObserversSaveDeleted() {
        for (Observer obs : observers) {
            obs.saveWasDeleted();
        }
    }

    public static void addObservers(Observer newObs) {
        observers.add(newObs);
    }

    public static void removeObservers(Observer obs) {
        observers.remove(obs);
    }
}