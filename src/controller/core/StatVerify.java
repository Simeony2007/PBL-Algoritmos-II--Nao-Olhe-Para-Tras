package src.controller.core;

import java.util.ArrayList;

import src.model.service.logic.Condition;
import src.model.service.logic.Npc;
import src.model.service.logic.Orfeu;
import src.model.service.logic.Scene;

public class StatVerify {

    private StatVerify(){}

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