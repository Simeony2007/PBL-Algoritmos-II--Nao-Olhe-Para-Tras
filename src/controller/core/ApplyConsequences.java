package src.controller.core;
import java.util.ArrayList;

import src.model.service.*;
import src.model.service.logic.Choice;
import src.model.service.logic.Npc;
import src.model.service.logic.Orfeu;

public class ApplyConsequences {

    private ApplyConsequences(){}

    public static String applyConsequences(Orfeu orfeu, ArrayList<Npc> npcs, Choice choice){
        orfeu.changeObol(choice.getObol());
        orfeu.changeLove(choice.getLove());
        orfeu.changeSadness(choice.getSadness());
        orfeu.changeAngry(choice.getAngry());
        if (choice.getNpcInChoice() != null){
           for (Npc npc : npcs) {
               if(npc.getName() == choice.getNpcInChoice()){
                   npc.changeAffinity(choice.getAffinity());
               }
           }
        }
        return choice.getNextSceneIdChoice();
    }
}
