package src.model.service;

import java.util.HashMap;
import java.util.Map;

import src.model.service.logic.Orfeu;

public class Collection {
    boolean final_1; // Final 1: Olhe para trás
    boolean final_2; // Final 2: Não olhe para trás
    boolean final_3; // Final 3: Punição Eterna
    boolean final_4; // Final 4: Orfeu fracassa e Sísifo escapa

    boolean chapter_1;
    boolean chapter_2;
    boolean chapter_3;
    boolean chapter_4;
    boolean chapter_5;
    boolean chapter_6;
    boolean chapter_7;
    boolean chapter_8;
    boolean chapter_9;
    boolean chapter_10;

    public Collection() {
        final_1 = false;
        final_2 = false;
        final_3 = false;
        final_4 = false;

        chapter_1 = false;
        chapter_2 = false;
        chapter_3 = false;
        chapter_4 = false;
        chapter_5 = false;
        chapter_6 = false;
        chapter_7 = false;
        chapter_8 = false;
        chapter_9 = false;
        chapter_10 = false;
    }

    public Map<String, Boolean> getCollectionStatus() {
        Map<String, Boolean> status = new HashMap<>();
        status.put("final_1", final_1);
        status.put("final_2", final_2);
        status.put("final_3", final_3);
        status.put("final_4", final_4);
        status.put("chapter_1", chapter_1);
        status.put("chapter_2", chapter_2);
        status.put("chapter_3", chapter_3);
        status.put("chapter_4", chapter_4);
        status.put("chapter_5", chapter_5);
        status.put("chapter_6", chapter_6);
        status.put("chapter_7", chapter_7);
        status.put("chapter_8", chapter_8);
        status.put("chapter_9", chapter_9);
        status.put("chapter_10", chapter_10);
        return status;
    }

    public void onChapterReached(int chapterNumber) {
        switch (chapterNumber) {
            case 1:
                chapter_1 = true;
                break;
            case 2:
                chapter_2 = true;
                break;
            case 3:
                chapter_3 = true;
                break;
            case 4:
                chapter_4 = true;
                break;
            case 5:
                chapter_5 = true;
                break;
            case 6:
                chapter_6 = true;
                break;
            case 7:
                chapter_7 = true;
                break;
            case 8:
                chapter_8 = true;
                break;
            case 9:
                chapter_9 = true;
                break;
            case 10:
                chapter_10 = true;
                break;
        }
    }

    public void onFinalReached(String finalSceneId) {
        switch (finalSceneId) {
            case "Final 1: Olhe para trás":
                final_1 = true;
                break;
            case "Final 2: Não olhe para trás":
                final_2 = true;
                break;
            case "Final 3: Punição Eterna":
                final_3 = true;
                break;
            case "Final 4: Orfeu fracassa e Sísifo escapa":
                final_4 = true;
                break;
        }
    }

}
