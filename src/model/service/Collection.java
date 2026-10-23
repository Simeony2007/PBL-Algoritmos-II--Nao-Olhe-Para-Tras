package src.model.service;

import java.util.HashMap;
import java.util.Map;

import src.view.MenuInterface;
import src.model.repository.SaveManager;

public class Collection {
    boolean final_1; // Final 1: Olhe para trás
    boolean final_2; // Final 2: Não olhe para trás
    boolean final_3; // Final 3: Punição Eterna
    boolean final_4; // Final 4: Orfeu fracassa e Sísifo escapa

    boolean achievement_1; // Achievement 1 - A Entrada do Submundo
    boolean achievement_2; // Achievement 2 - Campos de Asfódelos
    boolean achievement_3; // Achievement 3 - Campos Elíseos
    boolean achievement_4; // Achievement 4 - O Tártaro
    boolean achievement_5; // Achievement 5 - A Casa de Hades
    boolean achievement_6; // Achievement 6 - A volta pelo Tártaro
    boolean achievement_7; // Achievement 7 - A volta por Elíseos
    boolean achievement_8; // Achievement 8 - A volta por Asfódelo
    boolean achievement_9; // Achievement 9 - O Rio Estíge
    boolean achievement_10; // Achievement 10 - Não olhe para trás
    boolean achievement_11; // Achievement 11 - Olhe para Frente
    boolean achievement_12; // Achievement 12 - Desafiar o Destino

    public Collection() {
        final_1 = false;
        final_2 = false;
        final_3 = false;
        final_4 = false;

        achievement_1 = false;
        achievement_2 = false;
        achievement_3 = false;
        achievement_4 = false;
        achievement_5 = false;
        achievement_6 = false;
        achievement_7 = false;
        achievement_8 = false;
        achievement_9 = false;
        achievement_10 = false;
    }

    public Map<String, Boolean> getCollectionStatus() {
        Map<String, Boolean> status = new HashMap<>();
        status.put("final_1", final_1);
        status.put("final_2", final_2);
        status.put("final_3", final_3);
        status.put("final_4", final_4);
        status.put("achievement_1", achievement_1);
        status.put("achievement_2", achievement_2);
        status.put("achievement_3", achievement_3);
        status.put("achievement_4", achievement_4);
        status.put("achievement_5", achievement_5);
        status.put("achievement_6", achievement_6);
        status.put("achievement_7", achievement_7);
        status.put("achievement_8", achievement_8);
        status.put("achievement_9", achievement_9);
        status.put("achievement_10", achievement_10);
        status.put("achievement_11", achievement_11);
        status.put("achievement_12", achievement_12);
        return status;
    }

    public void onChapterReached(int achievementNumber) {
        switch (achievementNumber) {
            case 1:
                achievement_1 = true;
                break;
            case 2:
                achievement_2 = true;
                break;
            case 3:
                achievement_3 = true;
                break;
            case 4:
                achievement_4 = true;
                break;
            case 5:
                achievement_5 = true;
                break;
            case 6:
                achievement_6 = true;
                break;
            case 7:
                achievement_7 = true;
                break;
            case 8:
                achievement_8 = true;
                break;
            case 9:
                achievement_9 = true;
                break;
            case 10:
                achievement_10 = true;
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

    public void showEndings() {
        MenuInterface.showEndings(final_1, final_2, final_3, final_4);
    }

    public void showAchievements() {
        String[] achievementsName = {
            "A Entrada do Submundo",
            "Campos de Asfódelos",
            "Campos Elíseos",
            "O Tártaro",
            "A Casa de Hades",
            "A volta pelo Tártaro",
            "A volta por Elíseos",
            "A volta por Asfódelo",
            "O Rio Estíge",
            "Não olhe para trás",
            "Olhe para Frente",
            "Desafiar o Destino"
        };

        for (int i = 0; i < achievementsName.length; i++) {
            if (i >= 10) {
                checkAchievement(i);
            }
            MenuInterface.showAchievement(achievementsName[i], i + 1, getCollectionStatus().get("achievement_" + (i + 1)));                
        }
    }

    private void checkAchievement(int achievementNumber) {
        if (achievementNumber == 10) { // Achievement 11 - Olhe para Frente
            if (final_1 && final_2 && final_3 && final_4) {
                achievement_11 = true;
            }
        } else if (achievementNumber == 11) { // Achievement 12 - Desafiar o Destino
            if (final_1 && final_2 && final_3 && final_4 && achievement_1 && achievement_2 && achievement_3 && achievement_4 && achievement_5 && achievement_6 && achievement_7 && achievement_8 && achievement_9 && achievement_10) {
                achievement_12 = true;
            }
        }
        SaveManager.saveCollection(this);
    }
}
