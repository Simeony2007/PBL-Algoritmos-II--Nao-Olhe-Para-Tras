package src.model.service.logic;

import src.model.service.Prototype;

public class Npc implements Prototype {
	private String name;
	private int affinity;

	public Npc(String npcName, int npcAffinity) {
		this.name = npcName;
		this.affinity = npcAffinity;
	}

	public String getName() {
		return name;
	}

	public int getAffinity() {
		return affinity;
	}

	public void changeAffinity(int value) {
		// TODO: VERIFICAR AS AFINIDADES NEGATIVAS
		affinity = Math.max(0, Math.min(99, affinity + value));
	}

	@Override
	public Npc clone() {
		Npc clonedNpc = new Npc(this.name, this.affinity);
		return clonedNpc;
	}
}