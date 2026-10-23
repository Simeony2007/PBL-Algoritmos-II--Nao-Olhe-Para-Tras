package src.model.service;

import java.util.ArrayList;

import src.model.repository.StoryBuilder;
import src.model.service.logic.Npc;
import src.model.service.logic.Orfeu;

public class SaveState {
    public String saveName; // Acho que isso serve como o nome do player - Simeony
    public Orfeu orfeu;
    public ArrayList<Npc> npcs;
    public int chapterIndex;
    public String sceneId;

    // 1. Construtor Padrão: Usado quando NÃO existe um arquivo de save (Novo Jogo)
    // O Gson também usará este construtor antes de preencher com os dados do JSON
    public SaveState() {
        this.orfeu = StoryBuilder.getOrfeu(); // Cria o Orfeu zerado do seu StoryBuilder
        this.npcs = StoryBuilder.GetNpcs(); // Cria a lista de NPCs do seu StoryBuilder
        this.chapterIndex = 0; // Começa no Capítulo 1 (index 0)
        this.sceneId = null; // null fará o jogo carregar a cena padrão do capítulo
    }

    // 2. Construtor de Save: Usado na hora de empacotar os dados para salvar
    public SaveState(Orfeu orfeu, ArrayList<Npc> npcs, int chapterIndex, String sceneId) {
        this.orfeu = orfeu;
        this.npcs = npcs;
        this.chapterIndex = chapterIndex;
        this.sceneId = sceneId;
    }
}