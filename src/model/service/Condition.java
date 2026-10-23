package src.model.service;

import java.util.ArrayList;

public class Condition {
    private String nextSceneId;
    private String npcName;
    private int type;
    private int love;
    private int sadness;
    private int angry;
    private int affinity;

    public Condition(String nextSceneId, String npcName, int type, int affinity, int love, int sadness, int angry) {
        this.nextSceneId = nextSceneId;
        this.npcName = npcName;
        this.type = type;
        this.affinity = affinity;
        this.love = love;
        this.sadness = sadness;
        this.angry = angry;
    }

    public String getNextSceneId() { return nextSceneId; }
    public String getNpcName() { return npcName; }
    public int getType() { return type; }
    public int getLove() { return love; }
    public int getSadness() { return sadness; }
    public int getAngry() { return angry; }
    public int getAffinity() { return affinity; }

    // NOVA LÓGICA: A própria condição verifica se os atributos batem
    public boolean isMet(Orfeu orfeu, ArrayList<Npc> npcs) {
        if (npcName != null) {
            for (Npc npc : npcs) {
                if (npc.getName().equals(npcName)) {
                    return npc.getAffinity() >= this.affinity;
                }
            }
            return false;
        } else {
            switch (type) {
                case 1: return orfeu.getLove() >= this.love;
                case 2: return orfeu.getSadness() >= this.sadness;
                case 3: return orfeu.getAngry() >= this.angry;
                default: return false;
            }
        }
    }
}