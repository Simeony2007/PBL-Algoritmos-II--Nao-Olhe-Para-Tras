package src.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import src.view.GameInterfaceJFX;
import src.view.MenuInterfaceJFX;
import javafx.scene.Scene;

public class StageController extends Application {

    int buttonWidth = 100;
    Stage principalStage;
    Scene menuScene;
    ControllerFX controllerFx = new ControllerFX();
    MenuInterfaceJFX menuInterface = new MenuInterfaceJFX();
    GameInterfaceJFX gameInterface = new GameInterfaceJFX();

    @Override
    public void start(Stage principalStage) throws Exception {

        this.principalStage = principalStage;
        this.menuScene = menuInterface.menuScreen();

        principalStage.setHeight(600);
        principalStage.setWidth(800);
        principalStage.setScene(menuScene);
        principalStage.show();

    }

    public void setScene(Scene scn) {
        principalStage.setScene(scn);
    }

    public void close() {
        principalStage.close();
    }

    public void startGame() {
        principalStage.setScene(gameInterface.gameScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
