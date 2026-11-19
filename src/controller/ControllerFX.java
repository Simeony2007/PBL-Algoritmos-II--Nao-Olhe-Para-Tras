package src.controller;

import src.model.repository.SaveManager;

// FACADE ENTRE O GAME E O VIEWJFX 
public class ControllerFX {

    public void loader(int index) {
        SaveManager.loadGame(index);

    }
}
