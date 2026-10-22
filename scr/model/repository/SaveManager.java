package scr.model.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import scr.controller.Settings;
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
    public static final String SETTINGS_DIRECTORY = "saves/settings/";

    public static void saveGame(SaveState state, int slot) {

        if (slot == -1) {
            File directory = new File(SAVE_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            if (Settings.autoSaveCounter > 5) {
                Settings.autoSaveCounter = 1;
            }
            // 3. Append the folder path to the file name
            String fileName = SAVE_DIRECTORY + "auto_save_" + Settings.autoSaveCounter + ".json";
            try (FileWriter writer = new FileWriter(fileName)) {
                gson.toJson(state, writer);
                Settings.autoSaveCounter++;
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
        File file = new File(SAVE_DIRECTORY + "auto_save_" + slot + ".json");
        if (file.exists()) {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return formatador.format(new Date(file.lastModified()));
        } else {
            return null;
        }
    }

    public static void saveSettings(Settings currentSettings) {
        File directory = new File(SETTINGS_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = SETTINGS_DIRECTORY + "config.json";
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(currentSettings, writer);
        } catch (IOException e) {
            GameInterface.printWarning("\n[!] Erro crítico ao salvar o arquivo: " + e.getMessage());
        }

    }

    public static Settings loadSettings() {
        File file = new File(SETTINGS_DIRECTORY + "config.json");
        if (!file.exists()) {
            return new Settings();
        } else {
            try (FileReader reader = new FileReader(file)) {
                return gson.fromJson(reader, Settings.class);
            } catch (IOException e) {
                GameInterface
                        .printWarning(
                                "\n[!] Erro ao carregar. Reiniciando configurações. (" + e.getMessage() + ")");
                return new Settings();
            }
        }
    }
}