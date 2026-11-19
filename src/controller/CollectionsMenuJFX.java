package src.controller;

import java.util.List;

import src.model.repository.SaveManager;
import src.model.service.Collection;
import src.model.service.CollectionItem;

public class CollectionsMenuJFX {
    Collection collection;

    public CollectionsMenuJFX() {
        this.collection = SaveManager.loadCollection();
    }

    public List<CollectionItem> getEndingsList() {
        return collection.getEndings();
    }

    public List<CollectionItem> getAchievementsList() {
        return collection.getAchievements();
    }

}
