package src.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.model.repository.SaveManager;

public class Collection {
    boolean final_1; // Final 1: Olhe para trás
    boolean final_2; // Final 2: Não olhe para trás
    boolean final_3; // Final 3: Punição Eterna
    boolean final_4; // Final 4: Orfeu fracassa e Sísifo escapa

    boolean achievement_1;  // Achievement 1 - A Entrada do Submundo
    boolean achievement_2;  // Achievement 2 - Campos de Asfódelos
    boolean achievement_3;  // Achievement 3 - Campos Elíseos
    boolean achievement_4;  // Achievement 4 - O Tártaro
    boolean achievement_5;  // Achievement 5 - A Casa de Hades
    boolean achievement_6;  // Achievement 6 - A volta pelo Tártaro
    boolean achievement_7;  // Achievement 7 - A volta por Elíseos
    boolean achievement_8;  // Achievement 8 - A volta por Asfódelo
    boolean achievement_9;  // Achievement 9 - O Rio Estíge
    boolean achievement_10; // Achievement 10 - Não olhe para trás
    boolean achievement_11; // Achievement 11 - Todos os Finais
    boolean achievement_12; // Achievement 12 - Todas as Conquistas

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
        achievement_11 = false;
        achievement_12 = false;
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
        switch (achievementNumber + 1) {
            case 1 -> achievement_1 = true;
            case 2 -> achievement_2 = true;
            case 3 -> achievement_3 = true;
            case 4 -> achievement_4 = true;
            case 5 -> achievement_5 = true;
            case 6 -> achievement_6 = true;
            case 7 -> achievement_7 = true;
            case 8 -> achievement_8 = true;
            case 9 -> achievement_9 = true;
            case 10 -> achievement_10 = true;
        }
        checkAchievement();
        SaveManager.saveCollection(this);
    }

    public void onFinalReached(String finalSceneId) {
        switch (finalSceneId) {
            case "Final 1: Olhe para trás" -> final_1 = true;
            case "Final 2: Não olhe para trás" -> final_2 = true;
            case "Final 3: Punição Eterna" -> final_3 = true;
            case "Final 4: Orfeu fracassa e Sísifo escapa" -> final_4 = true;
        }
        checkAchievement();
        SaveManager.saveCollection(this);
    }

    private void checkAchievement() {
        checkAllEndingsUnlocked();
        checkAllAchievementsUnlocked();
    }

    private void checkAllEndingsUnlocked() {
        if (final_1 && final_2 && final_3 && final_4) {
            achievement_11 = true; // Achievement 11 - Olhe para Frente
        }
        SaveManager.saveCollection(this);
    }

    private void checkAllAchievementsUnlocked() {
        if (final_1 && final_2 && final_3 && final_4 && achievement_1 && achievement_2 && achievement_3 && achievement_4 && achievement_5 && achievement_6 && achievement_7 && achievement_8 && achievement_9 && achievement_10) {
            achievement_12 = true; // Achievement 12 - Desafiar o Destino
        }
        SaveManager.saveCollection(this);
    }

    public List<CollectionItem> getEndings() {
        List<CollectionItem> endings = new ArrayList<>();
        endings.add(new CollectionItem("Final 1: Olhe para trás", "Por falta de confiança, você optou por olhar para trás.", final_1));
        endings.add(new CollectionItem("Final 2: Não olhe para trás", "Orfeu alcançou o seu objetivo, salvando Euridice.", final_2));
        endings.add(new CollectionItem("Final 3: Punição Eterna", "Orfeu desrespeitou o Submundo e pagou o preço.", final_3));
        endings.add(new CollectionItem("Final 4: Orfeu fracassa e Sísifo escapa", "Orfeu falhou em sua missão, permitindo que Sísifo escapasse.", final_4));
        return endings;
    }

    public List<CollectionItem> getAchievements() {
        checkAchievement();

        List<CollectionItem> achievements = new ArrayList<>();
        achievements.add(new CollectionItem("A Entrada do Submundo", "Finalize o capítulo 1.", achievement_1));
        achievements.add(new CollectionItem("Campos de Asfódelos", "Finalize o capítulo 2.", achievement_2));
        achievements.add(new CollectionItem("Campos Elíseos", "Finalize o capítulo 3.", achievement_3));
        achievements.add(new CollectionItem("O Tártaro", "Finalize o capítulo 4.", achievement_4));
        achievements.add(new CollectionItem("A Casa de Hades", "Finalize o capítulo 5.", achievement_5));
        achievements.add(new CollectionItem("A volta pelo Tártaro", "Finalize o capítulo 6.", achievement_6));
        achievements.add(new CollectionItem("A volta por Elíseos", "Finalize o capítulo 7.", achievement_7));
        achievements.add(new CollectionItem("A volta por Asfódelo", "Finalize o capítulo 8.", achievement_8));
        achievements.add(new CollectionItem("O Rio Estíge", "Finalize o capítulo 9.", achievement_9));
        achievements.add(new CollectionItem("Não olhe para trás", "Finalize o capítulo 10.", achievement_10));
        achievements.add(new CollectionItem("Olhe para Frente", "Desbloqueie todos os 4 finais.", achievement_11));
        achievements.add(new CollectionItem("Desafiar o Destino", "Desbloqueie todas as conquistas.", achievement_12));
        return achievements;
    }
}
