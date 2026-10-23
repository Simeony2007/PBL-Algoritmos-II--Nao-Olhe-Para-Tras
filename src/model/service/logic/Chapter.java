package src.model.service.logic;

import java.util.ArrayList;

public class Chapter {
    private ArrayList<Scene> scenes = new ArrayList<>();
    private String title;
    private int currentSceneIndex = 0;

    public Chapter(String titleText) {
        this.title = titleText;
    }

    public void newScene(Scene scene) {
        scenes.add(scene);
    }

    public String getTitle() {
        return title;
    }

    public void changeCurrentScene(String sceneId) {
        for (int i = 0; i < scenes.size(); i++) {
            if (scenes.get(i).getSceneId().equals(sceneId)) {
                currentSceneIndex = i;
                break; 
            }
        }
    }

    public Scene getCurrentScene() {
        if (currentSceneIndex < scenes.size()) {
            return scenes.get(currentSceneIndex);
        }
        return null;
    }

    public void nextScene() {
        currentSceneIndex++;
    }

    public boolean isScenesFinished() {
        return currentSceneIndex >= scenes.size();
    }
}