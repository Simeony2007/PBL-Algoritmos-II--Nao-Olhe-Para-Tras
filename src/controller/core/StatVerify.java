package src.controller.core;

import java.util.ArrayList;

import src.model.service.Condition;
import src.model.service.Npc;
import src.model.service.Orfeu;
import src.model.service.Scene;

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