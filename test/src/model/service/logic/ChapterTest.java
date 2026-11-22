package src.model.service.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ChapterTest {

    private Chapter chapter;
    private ArrayList<Scene> scenes;

    @Before
    public void setUp() {
        chapter = new Chapter("TEST");
        scenes = new ArrayList<>();
        scenes.add(new Scene("id1"));
        scenes.add(new Scene("id2"));
        scenes.add(new Scene("id3"));
    }

    @Test
    public void chapterNavigatesByIndexAndSceneId() {
        assertEquals("TEST", chapter.getTitle());
        chapter.newScene(scenes.get(0));
        chapter.newScene(scenes.get(1));
        chapter.newScene(scenes.get(2));

        assertEquals(scenes.get(0), chapter.getCurrentScene());
        chapter.nextScene();
        assertEquals(scenes.get(1), chapter.getCurrentScene());
        chapter.nextScene();
        assertEquals(scenes.get(2), chapter.getCurrentScene());

        chapter.changeCurrentScene("id2");
        assertEquals(scenes.get(1), chapter.getCurrentScene());

        chapter.changeCurrentScene("missing");
        assertEquals(scenes.get(1), chapter.getCurrentScene());

        chapter.changeCurrentScene("id3");
        assertEquals(scenes.get(2), chapter.getCurrentScene());
        assertTrue(!chapter.isScenesFinished());
        chapter.nextScene();
        assertTrue(chapter.isScenesFinished());
        assertNull(chapter.getCurrentScene());
    }
}