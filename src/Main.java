package src;

import src.controller.menu.Menu;
import src.model.repository.StoryBuilder;

public class Main {
	public static void main(String[] args) {
		StoryBuilder.salvarHistoria();
		new Menu();
	}
}