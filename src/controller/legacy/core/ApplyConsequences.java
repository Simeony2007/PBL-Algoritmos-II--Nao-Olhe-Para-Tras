package src.controller.legacy.core;

import java.util.ArrayList;

import src.model.service.logic.Choice;
import src.model.service.logic.Npc;
import src.model.service.logic.Orfeu;
import src.view.legacy.GameInterface;

public class ApplyConsequences {

    private ApplyConsequences() {
    }

    public static String applyConsequences(Orfeu orfeu, ArrayList<Npc> npcs, Choice choice) {
        orfeu.changeObol(choice.getObol());
        orfeu.changeLove(choice.getLove());
        orfeu.changeSadness(choice.getSadness());
        orfeu.changeAngry(choice.getAngry());
        if (choice.getNpcInChoice() != null) {
            for (Npc npc : npcs) {
                if (npc.getName().equals(choice.getNpcInChoice())) {
                    System.out.println(choice.getAffinity()); // TODO: REMOVER
                    System.out.println(npc.getName()); // TODO: REMOVER
                    npc.changeAffinity(choice.getAffinity());
                }
            }
        }
        return choice.getNextSceneIdChoice();
    }

    private static String npcNameTranslator(String name) {
        switch (name) {
            case "Charon":
                return "Caronte";
            case "Eurydice":
                return "Euridice";
            case "Sisyphus":
                return "Sísifo";
            default:
                return "error";
        }
    }
}
