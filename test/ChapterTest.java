package test;

import org.junit.Before;
import org.junit.Test;

import src.model.service.Chapter;
import src.model.service.Scene;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class ChapterTest {

    private Chapter chapter;
    private ArrayList<Scene> scenes;

    @Before
    public void setUp(){
        chapter = new Chapter("TEST");
        scenes = new ArrayList<>();
        scenes.add(new Scene("id1"));
        scenes.add(new Scene("id2"));
        scenes.add(new Scene("id3"));
    }

    @Test
    public void chapterTest(){
        assertEquals("TEST", chapter.getTitle());
        chapter.newScene(scenes.get(0));
        assertEquals(scenes.get(0), chapter.getCurrentScene());
        chapter.newScene(scenes.get(1));
        chapter.newScene(scenes.get(2));
        chapter.nextScene();
        chapter.nextScene();
        assertEquals(scenes.get(2), chapter.getCurrentScene());
        chapter.changeCurrentScene("id2");
        assertEquals(scenes.get(1), chapter.getCurrentScene());
        chapter.nextScene();
        assertEquals(scenes.get(2), chapter.getCurrentScene());
        chapter.nextScene();
        assertTrue(chapter.isScenesFinished());
    }
}