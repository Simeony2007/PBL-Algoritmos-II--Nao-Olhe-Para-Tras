package src.view;

import java.util.Map;

public class MenuInterface extends Interface {
    // Main Menu
    public static void showMainMenu() {
        System.out.println("=================================");
        System.out.println("       NÃO OLHE PARA TRÁS        ");
        System.out.println("=================================");
        System.out.println("1 - Iniciar");
        System.out.println("2 - Configurações");
        System.out.println("3 - Coleção");
        System.out.println("4 - Instruções");
        System.out.println("5 - Créditos");
        System.out.println("6 - Fechar jogo");
        System.out.println("=================================");
        System.out.print("Escolha uma opção: ");
    }

    public static void showMenuInvalidOption() {
        System.out.println("\n[!] Opção inválida! Digite um número de 1 a 4.\n");
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
        showPressEnterToContinue();
    }

    public static void showCredits() {
        System.out.println("\n=== CRÉDITOS ===");
        System.out.println("Desenvolvido por: Pedro e Simeony\n");
        System.out
                .println("Universo e História inspirados na mitologia grega, em especial o conto de Orfeu e Eurídice");
        showPressEnterToContinue();
    }

    public static void showExitMessage() {
        System.out.println("\nAté a próxima!\n");
    }

    // Slot Menu

    public static void showLoadMenu(Map<Integer, String> informationDictionary) {
        System.out.println("\n=================================");
        System.out.println("       SELECIONAR SLOT           ");
        System.out.println("=================================");
        System.out.println("Escolha um slot para iniciar ou continuar:");
        if (informationDictionary != null) {
            for (int i = 1; i < 4; i++) {
                System.out.println(informationDictionary.get(i));
            }
            for (int i = 1; i < 6; i++) {
                int a = i + 3;
                System.out.println(informationDictionary.get(a));
            }
        }
        // System.out.print("Slot: ");
    }

    public static void showSlotSelection(int slot) {
        System.out.println("\n--- SLOT " + slot + " SELECIONADO ---");
        System.out.println("[1] Jogar");
        System.out.println("[2] Apagar Save");
        System.out.println("[3] Voltar ao Menu");
        System.out.print("Escolha uma opção: ");
    }

    // Settings Menu
    public static void showSettings(boolean autoSaveState, int textColorNumber, int textSpeedNumber) {
        String colorName;
        String autoSaveMode = "Desligado";
        String textSpeedText;

        switch (textColorNumber) {
            case 1:
                colorName = "Branco";
                break;
            case 2:
                colorName = "Azul";
                break;
            case 3:
                colorName = "Vermelho";
                break;
            case 4:
                colorName = "Roxo";
                break;
            default:
                colorName = "Branco";
                break;
        }

        switch (textSpeedNumber) {
            case 100:
                textSpeedText = "Rápido";
                break;
            case 1000:
                textSpeedText = "Médio";
                break;
            case 2500:
                textSpeedText = "Lento";
                break;
            default:
                textSpeedText = "Médio";
                break;
        }

        if (autoSaveState) {
            autoSaveMode = "Ligado";
        }

        System.out.println("--- Configurações ---");
        System.out.print("1 - AutoSave: ");
        System.out.println(autoSaveMode);
        System.out.print("2 - Cor do texto: ");
        System.out.println(colorName);
        System.out.print("3 - Velocidade do texto: ");
        System.out.println(textSpeedText);
        System.out.println("4 - Salvar e voltar ao menu principal");
        System.out.print("Digite sua escolha: ");
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

    // Collection Menu
    public static void showCollectionMenu() {
        System.out.println("\n--- Menu Coleção ---");
        System.out.println("1 - Finais");
        System.out.println("2 - Conquistas");
        System.out.println("3 - Voltar ao menu principal");
        System.out.print("Digite sua escolha: ");
    }

    public static void showEndings(boolean boolEnding1, boolean boolEnding2, boolean boolEnding3, boolean boolEnding4) {
        showEnding1(boolEnding1);
        showEnding2(boolEnding2);
        showEnding3(boolEnding3);
        showEnding4(boolEnding4);
        showPressEnterToContinue();
    }

    public static void showEnding1(boolean unlocked) {
        if (unlocked) {
            showText("\n--- Final 1: Olhe para trás ---");
            showText("Por falta de confiança, você optou por olhar para trás.");
        } else {
            showText("\n--- Final 1: ?????? ---");
            showText("Você ainda não desbloqueou este final.");
        }
    }

    public static void showEnding2(boolean unlocked) {
        if (unlocked) {
            showText("\n--- Final 2: Não olhe para trás ---");
            showText("Orfeu alcançou o seu objetivo, salvando Euridice.");
        } else {
            showText("\n--- Final 2: ?????? ---");
            showText("Você ainda não desbloqueou este final.");
        }
    }

    public static void showEnding3(boolean unlocked) {
        if (unlocked) {
            showText("\n--- Final 3: Punição Eterna ---");
            showText("Orfeu desrespeitou o Submundo e pagou o preço.");
        } else {
            showText("\n--- Final 3: ?????? ---");
            showText("Você ainda não desbloqueou este final.");
        }
    }

    public static void showEnding4(boolean unlocked) {
        if (unlocked) {
            showText("\n--- Final 4: Orfeu fracassa e Sísifo escapa ---");
            showText("Orfeu falhou em sua missão, permitindo que Sísifo escapasse.");
        } else {
            showText("\n--- Final 4: ?????? ---");
            showText("Você ainda não desbloqueou este final.");
        }
    }

    public static void showAchievement(String achievementName, int achievementNumber, boolean unlocked) {
        showText("\n--- Conquista " + achievementName + " ---");
        if (unlocked) {
            if (achievementNumber < 10) {
                showText("Conclua o Capítulo " + achievementNumber + " da jornada de Orfeu.");
            } else if (achievementNumber == 11) {
                showText("Descubra todos os desfechos da jornada de Orfeu.");
            } else if (achievementNumber == 12) {
                showText("Conquiste tudo o que o jogo tem a oferecer.");
            } else {
                showText("Error: Conquista não reconhecida.");
            }
        } else {
            showText("??????");
        }
    }
}
