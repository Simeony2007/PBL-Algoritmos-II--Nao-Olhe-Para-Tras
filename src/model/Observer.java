package src.model;

import java.util.ArrayList;

import src.model.service.logic.Npc;
import src.model.service.logic.Orfeu;

public interface Observer {

    public void modelUpdated(Orfeu orfeu, ArrayList<Npc> npc, Orfeu oldOrfeu, ArrayList<Npc> oldNpcs);

    public void saveWasMade();

}
