package src.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SettingsTest {
    private Settings settings;

    @Before
    public void setUp() {
        settings = new Settings();
    }

    @Test
    public void defaultsMatchSupportedSettings() {
        assertTrue(settings.isAutoSave());
        assertEquals("Ativado", settings.getAutoSaveString());
        assertEquals(1, settings.getAutoSaveCounter());
        assertEquals(35, settings.getTextSpeed());
        assertEquals("Médio", settings.getTextSpeedString());
        assertEquals(50, settings.getVolume());
    }

    @Test
    public void textSpeedCyclesThroughAllSupportedValues() {
        settings.changeTextSpeed();
        assertEquals(15, settings.getTextSpeed());
        assertEquals("Rápido", settings.getTextSpeedString());

        settings.changeTextSpeed();
        assertEquals(0, settings.getTextSpeed());
        assertEquals("Instantâneo", settings.getTextSpeedString());

        settings.changeTextSpeed();
        assertEquals(70, settings.getTextSpeed());
        assertEquals("Lento", settings.getTextSpeedString());

        settings.changeTextSpeed();
        assertEquals(35, settings.getTextSpeed());
    }

    @Test
    public void autosaveAndVolumeCanBeChanged() {
        settings.toggleAutoSave();
        settings.setVolume(75);

        assertFalse(settings.isAutoSave());
        assertEquals("Desativado", settings.getAutoSaveString());
        assertEquals(75, settings.getVolume());
    }
}
