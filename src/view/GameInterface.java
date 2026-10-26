package src.view;

public class GameInterface extends Interface {
    public static final int MAX_COLLORS = 4;

    private GameInterface() {
    }

    public static void printWarning(String text) {
        System.out.println("---WARNING---");
        System.out.println(text);
    }

    // Classe feita utilizando IA
    public static void showDialogue(String text) {
        int wrapLength = 160;
        String indent = "    ";

        String[] words = text.split(" ");

        StringBuilder wrappedText = new StringBuilder(indent);
        int currentLineLength = indent.length();

        for (String word : words) {
            if (currentLineLength + word.length() > wrapLength) {
                wrappedText.append("\n");
                currentLineLength = 0;
            }
            wrappedText.append(word).append(" ");
            currentLineLength += word.length() + 1;
        }

        System.out.println(wrappedText.toString());
    }

    // --- MÉTODOS DE TEXTO DO JOGO ---
    public static void showChapterTitle(String title) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(title);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    public static void showPlayerStats(String stats) {
        System.out.println("---------------------------------");
        System.out.println(stats);
        System.out.println("---------------------------------");
    }

    public static void showPromptChoice() {
        System.out.print("\nO que você faz? Escolha uma opção (ou digite 'salvar'): ");
    }

    public static void showGameInvalidOption() {
        System.out.println("\n[!] Opção inválida! Escolha um número da lista.\n");
    }

    public static void waitForEnter(String mensagem) {
        System.out.println(mensagem);
        getTextInput();
    }

    public static void showGameInvalidInput() {
        System.out.println("\n[!] Entrada inválida! Por favor, digite apenas números.\n");
    }

    public static void showGameFinished() {
        System.out.println("\n=================================");
        System.out.println("             FIM                 ");
        System.out.println("=================================\n");
    }

    public static void showOverwriteQuestion() {
        System.out.println("Deseja sobrescrever o Save atual?");
        System.out.print("Digite(S/N): ");
    }

    public static void clearConsoleSave(int lines) {
    for (int i = 0; i < lines; i++) {
        System.out.print("\033[1A");
        System.out.print("\033[2K");
    }
    System.out.flush();
}

}