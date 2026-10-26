package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import src.model.customExceptions.SaveNotFoundException;
import src.model.repository.SaveManager;
import src.model.service.SaveState;
import src.model.service.Settings;
import src.model.service.logic.Npc;
import src.model.service.logic.Orfeu;

public class SaveAndLoadTest {

    @Before
    public void setUp() {
        try {
            if (SaveManager.doesSaveExist(1)) {
                SaveManager.deleteSave(1);
            }
            if (SaveManager.doesSaveExist(2)) {
                SaveManager.deleteSave(2);
            }
            if (SaveManager.doesSaveExist(3)) {
                SaveManager.deleteSave(3);
            }

        } catch (SaveNotFoundException e) {
            System.out.println("Save já inexistente, deletando outro...");
        }
    }

    @Test
    public void settingsAreCreatedSavedAndLoaded() throws IOException {
        Settings settings = new Settings();
        settings.setAutosave();
        settings.changeColorNumber();
        settings.changeTextSpeed();
        settings.addAutoSaveCounter();

        SaveManager.saveSettings(settings);

        Settings loadedSettings = SaveManager.loadSettings();

        assertNotNull(loadedSettings);

        assertFalse(loadedSettings.isAutoSave());
        assertEquals(2, loadedSettings.getColorNum());
        assertEquals(100, loadedSettings.getTextSpeed());
        assertEquals(2, loadedSettings.getAutoSaveCounter());
    }

    @Test
    public void gameSaveIsCreatedLoadedAndDeleted() throws IOException, SaveNotFoundException {
        Orfeu orfeu = new Orfeu(25, 40, 15, 30);
        ArrayList<Npc> npcs = new ArrayList<>();
        npcs.add(new Npc("Charon", 65));
        SaveState save = new SaveState(orfeu, npcs, 2, "scene-3");

        SaveManager.saveGame(save, 3);

        assertTrue(SaveManager.doesSaveExist(3));
        assertFalse(SaveManager.doesSaveExist(2));

        SaveState loadedSave = SaveManager.loadGame(3);

        assertNotNull(loadedSave);
        assertEquals(25, loadedSave.orfeu.getObol());
        assertEquals(40, loadedSave.orfeu.getLove());
        assertEquals(15, loadedSave.orfeu.getAngry());
        assertEquals(30, loadedSave.orfeu.getSadness());
        assertEquals(1, loadedSave.npcs.size());
        assertEquals("Charon", loadedSave.npcs.get(0).getName());
        assertEquals(65, loadedSave.npcs.get(0).getAffinity());
        assertEquals(2, loadedSave.chapterIndex);
        assertEquals("scene-3", loadedSave.sceneId);

        SaveManager.deleteSave(3);
        assertFalse(SaveManager.doesSaveExist(3));
    }
}
