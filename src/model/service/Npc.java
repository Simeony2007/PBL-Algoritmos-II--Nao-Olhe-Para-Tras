package src.model.service;
public class Npc{
	private String name;
	private int affinity;

	public Npc(String npcName, int npcAffinity){
		this.name = npcName;
		this.affinity = npcAffinity;
	}

	public String getName(){
		return name;
	}
	public int getAffinity(){
		return affinity;
	}
	public void changeAffinity(int value){
        affinity = Math.max(0, Math.min(99, affinity + value));
    }
}