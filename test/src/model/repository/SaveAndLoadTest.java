package src.model.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import src.model.customExceptions.SaveNotFoundException;
import src.model.service.SaveState;
import src.model.service.Settings;
import src.model.service.logic.Npc;
import src.model.service.logic.Orfeu;

public class SaveAndLoadTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private String previousDataDirectory;

    @Before
    public void setUp() throws Exception {
        previousDataDirectory = System.getProperty("pbl.data.directory");
        System.setProperty(
                "pbl.data.directory",
                temporaryFolder.newFolder("game-data").getAbsolutePath());
    }

    @After
    public void restoreDataDirectory() {
        if (previousDataDirectory == null) {
            System.clearProperty("pbl.data.directory");
        } else {
            System.setProperty("pbl.data.directory", previousDataDirectory);
        }
    }

    @Test
    public void settingsCanBeSavedAndLoaded() {
        Settings defaults = SaveManager.loadSettings();
        assertTrue(defaults.isAutoSave());
        assertEquals(1, defaults.getAutoSaveCounter());
        assertEquals(35, defaults.getTextSpeed());
        assertEquals(50, defaults.getVolume());

        defaults.toggleAutoSave();
        defaults.changeTextSpeed();
        defaults.setVolume(75);
        SaveManager.saveSettings(defaults);

        Settings loadedSettings = SaveManager.loadSettings();
        assertNotNull(loadedSettings);
        assertFalse(loadedSettings.isAutoSave());
        assertEquals(15, loadedSettings.getTextSpeed());
        assertEquals(75, loadedSettings.getVolume());
    }

    @Test
    public void manualSaveCanBeCreatedLoadedAndDeleted() throws SaveNotFoundException {
        ArrayList<Npc> npcs = new ArrayList<>();
        npcs.add(new Npc("Charon", 65));
        SaveState save = new SaveState(new Orfeu(25, 40, 15, 30), npcs, 2, "scene-3");

        SaveManager.saveGame(save, 3);

        assertTrue(SaveManager.doesSaveExist(3));
        assertFalse(SaveManager.doesSaveExist(2));
        assertNotNull(SaveManager.getSaveDateTime(3));

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
        assertNull(SaveManager.getSaveDateTime(3));
        try {
            SaveManager.deleteSave(3);
            fail("Deleting a missing save should throw SaveNotFoundException");
        } catch (SaveNotFoundException expected) {
            assertEquals("Save do slot 3 nunca criado ou já deletado!", expected.getMessage());
        }
    }

    @Test
    public void autosavesRotateAfterFiveSlots() throws SaveNotFoundException {
        for (int chapterIndex = 0; chapterIndex < 6; chapterIndex++) {
            SaveManager.saveGame(
                    new SaveState(new Orfeu(1, 2, 3, 4), new ArrayList<Npc>(), chapterIndex, null),
                    -1);
        }

        assertEquals(5, SaveManager.loadGame(4).chapterIndex);
        assertNotNull(SaveManager.getAutoSaveDateTime(1));
        assertEquals(2, SaveManager.loadSettings().getAutoSaveCounter());

        SaveManager.deleteSave(4);
        assertNull(SaveManager.getAutoSaveDateTime(1));
    }
}
