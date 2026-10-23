package scr.controller;

import java.util.ArrayList;
import scr.model.service.Scene;
import scr.model.service.Npc;
import scr.model.service.Orfeu;
import scr.model.service.Condition;

public class StatVerify {

    private StatVerify() {
    }

    public static String selectScene(Orfeu orfeu, ArrayList<Npc> npcs, Scene scene) {
        Condition condition = scene.getCondition();

        if (condition == null) {
            return null;
        }

        // Delega a pergunta para a própria classe Condition
        if (condition.isMet(orfeu, npcs)) {
            return condition.getNextSceneId();
        }

        return null;
    }
}