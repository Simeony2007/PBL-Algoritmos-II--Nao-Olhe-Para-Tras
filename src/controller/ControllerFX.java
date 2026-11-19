package src.controller;

import src.model.repository.SaveManager;
import src.model.service.Collection;
import src.model.service.SaveState;
import src.model.service.Settings;
import src.controller.legacy.core.Game;

// FACADE ENTRE O GAME E O VIEWJFX 
public class ControllerFX {

    private Game game;

    public void loader(int index) {
        SaveState savestate = SaveManager.loadGame(index);
        Collection collection = SaveManager.loadCollection();
        Settings settings = SaveManager.loadSettings();
        game = new Game(savestate, collection, settings);
    }

    public void loader(int index, Collection collection) {
        SaveState savestate = SaveManager.loadGame(index);
        Settings settings = SaveManager.loadSettings();
        game = new Game(savestate, collection, settings);
    }

}
