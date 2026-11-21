package src.controller;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle.Control;

import src.controller.legacy.menu.MenuSlots;
import src.model.repository.SaveManager;
import src.model.service.CollectionItem;
import src.model.service.SaveState;
import src.controller.ControllerFX;

public class SlotsMenuJFX {

    MenuSlots menuSlots;
    ControllerFX control;

    public SlotsMenuJFX(ControllerFX fxctrl) {
        menuSlots = new MenuSlots();
        control = fxctrl;
    }

    public Map<Integer, String> getAllSaves() {
        return menuSlots.getAllSavesInformation();
    }

    public void deleteSave(int index) {
        control.deleteSave(index);
    }

    public void loadSave(int index) {
        control.loader(index);
    }

    public ControllerFX getControlFx() {
        return control;
    }

}
