package src.model.service.logic;

public class Choice{
	private String text;
	private String nextSceneId;
	private String npc;
	private int obol;
	private int affinity;
	private int love;
	private int angry;
	private int sadness;

	/**
	 * Construtor com NPC para modificações dependendo a escolha.
	 * @param text
	 * @param nextSceneId
	 * @param npcName
	 * @param obol
	 * @param affinity
	 * @param love
	 * @param angry
	 * @param sadness
	 */
	public Choice(String text, String nextSceneId, String npcName, int obol, int affinity, int love, int angry, int sadness){
		this.text = text;
		this.nextSceneId = nextSceneId;
		this.npc = npcName;
		this.obol = obol;
		this.affinity = affinity;
		this.love = love;
		this.angry = angry;
		this.sadness = sadness;
	}

	/**
	 * Construtor sem NPC para escolha.
	 * @param text
	 * @param nextSceneId
	 * @param obol
	 * @param affinity
	 * @param love
	 * @param angry
	 * @param sadness
	 */
	public Choice(String text, String nextSceneId, int obol, int affinity, int love, int angry, int sadness){
		this.text = text;
		this.nextSceneId = nextSceneId;
		this.obol = obol;
		this.affinity = affinity;
		this.love = love;
		this.angry = angry;
		this.sadness = sadness;
	}

	/**
	 * 
	 * @return Texto da Escolha
	 */
	public String getText(){
		return this.text;
	}

	/**
	 * 
	 * @return Id textual da próxima Cena.
	 */
	public String getNextSceneIdChoice(){
		return nextSceneId;
	}

	/**
	 * 
	 * @return Retorna o nome do NPC em escolha.
	 */
	public String getNpcInChoice(){
		return npc;
	}

	/**
	 * 
	 * @return Valor numérico do Obolo.
	 */
	public int getObol(){
		return obol;
	}
	
	/**
	 * 
	 * @return Valor numérico de Affinity.
	 */
	public int getAffinity(){
		return affinity;
	}

	/**
	 * 
	 * @return Valor numérico do Love.
	 */
	public int getLove(){
		return love;
	}

	/**
	 * 
	 * @return Valor numérico do Sadness.
	 */
	public int getSadness(){
		return sadness;
	}

	/**
	 * 
	 * @return Valor numérico da Angry.
	 */
	public int getAngry(){
		return angry;
	}
}