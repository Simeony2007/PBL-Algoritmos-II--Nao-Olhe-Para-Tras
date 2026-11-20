package src.controller;

import java.util.List;
import java.util.Map;

import src.controller.legacy.menu.MenuSlots;
import src.model.repository.SaveManager;
import src.model.service.CollectionItem;
import src.model.service.SaveState;
import src.controller.ControllerFX;

public class SlotsMenuJFX {

    MenuSlots menuSlots;
    ControllerFX control;

    public SlotsMenuJFX() {
        menuSlots = new MenuSlots();
        control = new ControllerFX();
    }

    public Map<Integer, String> getAllSaves() {
        return menuSlots.getAllSavesInformation();
    }

    public ControllerFX loadSave(int index) {
        control.loader(index);
        return control;
    }

}
