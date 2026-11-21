package src.view.Game;

import javafx.scene.layout.StackPane;
import src.controller.ControllerFX;
import src.controller.SlotsMenuJFX;
import src.view.Menu.SlotsJFX;

final class InGameSaveMenuJFX {

    private final ControllerFX controller;
    private final SlotsMenuJFX slotsController;
    private final SlotsJFX slotsView;
    private final StackPane view;

    public InGameSaveMenuJFX(ControllerFX controller, NotificationOverlayJFX notifications) {
        this.controller = controller;
        slotsController = new SlotsMenuJFX(controller, notifications);
        slotsView = new SlotsJFX(notifications);
        view = slotsView.simpleSaveMenuMounter(
                slotsController.getAllSaves(),
                this::save,
                this::delete,
                slotsController::doesSaveExist);
    }

    public StackPane getView() {
        return view;
    }

    public void show() {
        refreshSlots();
        view.setVisible(true);
    }

    public void hide() {
        view.setVisible(false);
    }

    private void save(int slot) {
        controller.saveGame(slot);
        refreshSlots();
    }

    private void delete(int slot) {
        slotsController.deleteSave(slot);
        refreshSlots();
    }

    private void refreshSlots() {
        slotsView.updateSimpleSaveInformation(
                slotsController.getAllSaves(),
                slotsController::doesSaveExist);
    }
}
