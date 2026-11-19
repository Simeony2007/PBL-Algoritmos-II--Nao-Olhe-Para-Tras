package src.model.service.logic;

import java.util.HashMap;
import java.util.Map;

public class Dialogue {

	// Texto da fala
	private String text;
	// private String dialogueId; Tava sem uso
	private String npcThatSaidIt;

	/**
	 * Construtor ao qual recebe um texto
	 * e o nome do NPC ao qual disse o diálogo.
	 * 
	 * @param text
	 * @param npcName
	 */
	public Dialogue(String text, String npcName) {
		this.text = text;
		this.npcThatSaidIt = npcName;
	}

	/**
	 * Construtor ao qual recebe apenas um texto
	 * deixando quem falou como Nulo
	 * 
	 * @param text
	 */
	public Dialogue(String text) {
		this.text = text;
		this.npcThatSaidIt = null;
	}

	// public String getDialogueId(){
	// return this.dialogueId;
	// }

	/**
	 * Pega o texto guardado no atributo junto ao nome do NPC ou não.
	 * 
	 * @return Texto guardado no atributo, caso tenha nome
	 *         no NPC, ele adiciona o nome e a fala
	 */
	public Map.Entry<String, String> getTexto() {
		Map.Entry<String, String> dic = Map.entry(npcThatSaidIt, text);
		return dic;
	}

	/**
	 * 
	 * @return Nome do NPC guardado no diálogo
	 */
	public String getNpcThatSaidIt() {
		return npcThatSaidIt;
	}
}