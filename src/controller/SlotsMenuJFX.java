package src.controller;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle.Control;

import src.controller.legacy.menu.MenuSlots;
import src.model.customExceptions.SaveNotFoundException;
import src.model.repository.SaveManager;
import src.model.service.CollectionItem;
import src.model.service.SaveState;
import src.view.Game.NotificationOverlayJFX;
import src.controller.ControllerFX;

public class SlotsMenuJFX {

    MenuSlots menuSlots;
    ControllerFX control;
    private final NotificationOverlayJFX errorNotification;

    public SlotsMenuJFX(ControllerFX fxctrl, NotificationOverlayJFX errorNotification) {
        menuSlots = new MenuSlots();
        control = fxctrl;
        this.errorNotification = errorNotification;
    }

    public Map<Integer, String> getAllSaves() {
        return menuSlots.getAllSavesInformation();
    }

    public boolean doesSaveExist(int index) {
        return SaveManager.doesSaveExist(index);
    }

    public void deleteSave(int index) {
        try {
            control.deleteSave(index);
        } catch (SaveNotFoundException e) {
            errorNotification.showNotification("SaveNotFound");
        }

    }

    public void loadSave(int index) {
        control.loader(index);
    }

    public ControllerFX getControlFx() {
        return control;
    }

}
