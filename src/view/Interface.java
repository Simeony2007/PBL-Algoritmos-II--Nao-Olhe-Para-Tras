package src.view;

import java.util.Scanner;

public abstract class Interface {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getTextInput() {
        return scanner.nextLine();
    }

    public static void showText(String text) {
        System.out.println(text);
    }

    // Classe feita utilizando IA
    public static void showTextLn(String text) {
        int wrapLength = 160;
        String[] words = text.split(" ");
        StringBuilder wrappedText = new StringBuilder();
        int currentLineLength = 0;

        for (String word : words) {
            if (currentLineLength + word.length() > wrapLength) {
                wrappedText.append("\n");
                currentLineLength = 0;
            }
            wrappedText.append(word).append(" ");
            currentLineLength += word.length() + 1;
        }
        System.out.println(wrappedText.toString().trim());
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void showMenuInvalidInput() {
        System.out.println("\n[!] Entrada inválida! Digite apenas números.\n");
    }

    public static void showPressEnterToContinue() {
        System.out.println("\n(Pressione ENTER para voltar)");
    }
}
