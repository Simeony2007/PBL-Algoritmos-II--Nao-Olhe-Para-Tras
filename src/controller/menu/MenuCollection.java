package src.controller.menu;

import src.model.service.Collection;
import src.view.MenuInterface;

public class MenuCollection {
    Collection collection;

    public MenuCollection(Collection collection) {
        this.collection = collection;
        iniciar();
    }

    private void iniciar() {
        int choice = 0;
        while (choice != 3) {
            choice = getCollectionMenuOption();
            MenuInterface.clearConsole();
            if (choice == 1) {
                collection.showEndings();
                MenuInterface.getTextInput();
            } else if (choice == 2) {
                collection.showAchievements();
                MenuInterface.getTextInput();
            }
            MenuInterface.clearConsole();
        }
        MenuInterface.clearConsole();}

    private int getCollectionMenuOption() {
        while (true) {
            try {
                MenuInterface.showCollectionMenu();
                String userInput = MenuInterface.getTextInput();
                int number = Integer.parseInt(userInput.trim());
                if (number > 0 && number <= 3) {
                    return number;
                } else {
                    MenuInterface.clearConsole();
                    MenuInterface.showTextLn("Digite números entre 1 e 3");
                }
            } catch (NumberFormatException e) {
                MenuInterface.clearConsole();
                MenuInterface.showMenuInvalidInput();
            }
        }
    }


}
