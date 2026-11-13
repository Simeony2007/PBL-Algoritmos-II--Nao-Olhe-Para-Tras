package src.model.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import src.model.service.SaveState;
import src.model.service.logic.Chapter;
import src.model.service.logic.Condition;
import src.model.service.logic.Npc;
import src.model.service.logic.Orfeu;
import src.model.service.logic.Scene;

public class StoryBuilder {
        public static Object BuildGame;
        public static final String HISTORY_DIRECTORY = "history/";

        public static Orfeu getOrfeu() {
                Orfeu orfeu = new Orfeu(1, 0, 0, 0);
                return orfeu;
        }

        public static ArrayList<Npc> GetNpcs() {
                ArrayList<Npc> npcs = new ArrayList<>();

                Npc charon = new Npc("Charon", 0);
                Npc eurydice = new Npc("Eurydice", 0);
                Npc sisyphus = new Npc("Sisyphus", 0);

                npcs.add(charon);
                npcs.add(eurydice);
                npcs.add(sisyphus);

                return (npcs);
        }

        public static ArrayList<Chapter> getChapters() throws FileNotFoundException, IOException {
                ArrayList<Chapter> chapters = new ArrayList<>();

                for (int i = 1; i < 11; i++) {

                        chapters.add(loadChapter(i));

                }
                return chapters;
        }

        private static Chapter loadChapter(int chapterNumber) throws FileNotFoundException, IOException {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                File file = new File(HISTORY_DIRECTORY + "Chapter" + chapterNumber + ".json");

                if (!file.exists()) {
                        throw new FileNotFoundException("Não encontrado");
                }

                try (FileReader reader = new FileReader(file)) {
                        return gson.fromJson(reader, Chapter.class);
                }
        }

}