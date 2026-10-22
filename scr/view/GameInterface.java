package scr.view;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class GameInterface {
    private static final Scanner scanner = new Scanner(System.in);
    public static final int MAX_COLLORS = 3;

    private GameInterface() {
    }

    public static String getTextInput() {
        return scanner.nextLine();
    }

    public static void showText(String text) {
        System.out.print(text);
    }

    // Feito utilizando IA
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

    public static void printWarning(String text) {
        System.out.println("---WARNING---");
        System.out.println(text);
    }

    // Feito utilizando IA
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

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // --- MÉTODOS DE TEXTO DO MENU ---
    public static void showMainMenu() {
        System.out.println("=================================");
        System.out.println("       NÃO OLHE PARA TRÁS        ");
        System.out.println("=================================");
        System.out.println("1 - Iniciar jogo");
        System.out.println("2 - Instruções");
        System.out.println("3 - Créditos");
        System.out.println("4 - Definições");
        System.out.println("5 - Fechar jogo");
        System.out.println("=================================");
        System.out.print("Escolha uma opção: ");
    }

    public static void showMenuInvalidOption() {
        System.out.println("\n[!] Opção inválida! Digite um número de 1 a 4.\n");
    }

    public static void showMenuInvalidInput() {
        System.out.println("\n[!] Entrada inválida! Digite apenas números.\n");
    }

    public static void showInstructions() {
        System.out.println("\n=== INSTRUÇÕES ===");
        System.out.println("Objetivo:\n- Atravessar o Submundo e recuperar sua Amada.\n");
        System.out.println("Como Jogar:\n- Digite no terminal um dos números das opções para selecionar elas.\n");
        System.out.println(
                "Mecânicas:\n- Orfeu possui 3 atributos, Amor, Raiva e Tristeza, eles aumentam ou diminuem conforme suas escolhas");
        System.out.println("- Orfeu também irá coletar óbolos (moedas) para atravessar o Submundo.");
        System.out.println("- Você também fará relacionamento com alguns personagens.");
        System.out.println("- Tanto o relacionameto, quanto os atributos terão consequências futuras.");
        System.out.println("\nPara salvar manualmente, digite \"salvar\" no momento onde uma escolha for exigida.");
        System.out.println("\n(Pressione ENTER para voltar)");
    }

    public static void showCredits() {
        System.out.println("\n=== CRÉDITOS ===");
        System.out.println("Desenvolvido por: Pedro e Simeony\n");
        System.out
                .println("Universo e História inspirados na mitologia grega, em especial o conto de Orfeu e Eurídice");
        System.out.println("\n(Pressione ENTER para voltar)");
    }

    public static void showExitMessage() {
        System.out.println("\nAté a próxima!\n");
    }

    public static void showLoadMenu(Map informationDictionary) {
        System.out.println("\n=================================");
        System.out.println("       SELECIONAR SLOT           ");
        System.out.println("=================================");
        System.out.println("Escolha um slot (1, 2 ou 3) para iniciar ou continuar:");
        if (informationDictionary != null) {
            for (int i = 1; i < 4; i++) {
                System.out.println(informationDictionary.get(i));
            }
            for (int i = 1; i < 6; i++) {
                int a = i + 3;
                System.out.println(informationDictionary.get(a));
            }
        }
        System.out.print("Slot: ");
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

    public static void changeColor(int colorNumber) {

        switch (colorNumber) {
            case 1:
                System.out.print("\u001B[0m");
                break;

            case 2:
                System.out.print("\u001B[34m");
                break;
            case 3:
                System.out.println("\u001B[31m");
                break;
            case 4:
                System.out.println("\u001B[35m");
                break;
            default:
                System.out.print("\u001B[0m");
                break;
        }

    }

    public static void showDefinitions(boolean autoSaveState, int textCollorNumber) {
        // Falta fazer o menu de Settings e mostrar as settings atuais e suas possíveis
        // mudanças - Simeony

        String collorName;
        String autoSaveMode = "Desligado";
        switch (textCollorNumber) {
            case 1:
                collorName = "Branco";
                break;
            case 2:
                collorName = "Azul";
                break;
            default:
                collorName = "Branco";
                break;
        }

        if (autoSaveState) {
            autoSaveMode = "Ligado";
        }

        System.out.println("--- Configurações ---");
        System.out.print("1 - AutoSave: ");
        System.out.println(autoSaveMode);
        System.out.print("2 - Cor do texto: ");
        System.out.println(collorName);
        System.out.print("3 - Voltar para o menu inicial ");
        System.out.println();
        System.out.print("Digite sua escolha: ");
    }

    public static void showOverwriteQuestion() {
        System.out.println("Deseja sobrescrever o Save atual?");
        System.out.print("Digite(S/N): ");
    }
}