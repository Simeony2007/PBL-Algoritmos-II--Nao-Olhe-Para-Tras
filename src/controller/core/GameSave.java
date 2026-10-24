package src.controller.core;

import src.model.repository.SaveManager;
import src.model.service.GameState;
import src.model.service.SaveState;

public class GameSave {
    private GameSave(){}

    public static void saveCurrentProgress(GameState state, String currentSceneId, int slot) {
        SaveState saveInfo = new SaveState(
                state.getOrfeu(),
                state.getNpcs(),
                state.getCurrentChapterIndex(),
                currentSceneId);
        SaveManager.saveGame(saveInfo, slot);
    }

}
