package src.model.service.logic;

import java.util.ArrayList;

public class Scene {
    private ArrayList<Dialogue> dialogues = new ArrayList<>();
    private ArrayList<Choice> choices = new ArrayList<>();
    private String sceneId;
    private int currentDialogueID = 0;
    private String nextSceneId;
    private Condition condition = null;
    private boolean isEnding = false;

    public Scene(String sceneIdText) {
        this.sceneId = sceneIdText;
        this.nextSceneId = null;
    }

    public Scene(String sceneIdText, String nextSceneIdName) {
        this.sceneId = sceneIdText;
        this.nextSceneId = nextSceneIdName;
    }

    // Getters e Setters de Finalização
    public void setEnding(boolean isEnding) {
        this.isEnding = isEnding;
    }

    public boolean isEnding() {
        return this.isEnding;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Condition getCondition() {
        return this.condition;
    }

    public String getNextSceneId() {
        return nextSceneId;
    }

    public void addNewDialogue(String textOfDialogue, String npcName) {
        Dialogue generic = new Dialogue(textOfDialogue, npcName);
        dialogues.add(generic);
    }

    public void addNewDialogue(String textOfDialogue) {
        Dialogue generic = new Dialogue(textOfDialogue);
        dialogues.add(generic);
    }

    public void addNewChoice(String text, String nextSceneId, int obol, int affinity, int love, int angry, int sadness) {
        Choice generic = new Choice(text, nextSceneId, obol, affinity, love, angry, sadness);
        choices.add(generic);
    }

    public void addNewChoice(String text, String nextSceneId, String npcName, int obol, int affinity, int love, int angry, int sadness) {
        Choice generic = new Choice(text, nextSceneId, npcName, obol, affinity, love, angry, sadness);
        choices.add(generic);
    }

    public String getSceneId() {
        return sceneId;
    }

    public boolean isFinishedAllDialogues() {
        return currentDialogueID >= dialogues.size();
    }

    public int choicesSize() {
        return choices.size();
    }

    public String nextDialogue() {
        return dialogues.get(currentDialogueID++).getTexto();
    }

    public String getChoicesText() {
        String generic = "";
        for (int i = 0; i < choices.size(); i++) {
            generic += (i + 1) + " - " + choices.get(i).getText() + "\n";
        }
        return generic;
    }

    public Choice getChoice(int id) {
        return choices.get(id);
    }
}