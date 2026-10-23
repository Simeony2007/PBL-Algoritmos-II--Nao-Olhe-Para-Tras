package src.controller.menu;

import src.model.repository.SaveManager;
import src.model.service.Collection;
import src.view.GameInterface;

public class MenuCollection {
    Collection collection;

    public MenuCollection(Collection collection) {
        this.collection = collection;
        iniciar();
    }

    private void iniciar() {
        GameInterface.showCollection(collection.getCollectionStatus());
        SaveManager.saveCollection(collection);
        GameInterface.clearConsole();
    }
}
