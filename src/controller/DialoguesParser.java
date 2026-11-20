package src.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DialoguesParser {

    private static final List<String> PERSONAGENS = List.of(
            "Orfeu", "Caronte", "Eurídice", "Sísifo", "Hades",
            "Perséfone", "Jasão", "Marinheiro", "Fã", "Guerreiro",
            "Alma Esquecida", "Alma Nobre", "Alma dos Elíseos");

    public static ArrayList<Map.Entry<String, String>> dialogueSeparator(
            ArrayList<Map.Entry<String, String>> dialogoOriginal) {

        ArrayList<Map.Entry<String, String>> resultado = new ArrayList<>();
        for (Map.Entry<String, String> i : dialogoOriginal) {

            String nome = i.getKey();
            String fala = i.getValue();
            int index = fala.indexOf(":");

            if (fala == null || (nome != null && !nome.isEmpty()) || index == -1) {
                resultado.add(i);
            } else {
                String prefixoDaFala = fala.substring(0, index).trim();
                boolean ehPersonagem = PERSONAGENS.contains(prefixoDaFala);

                if (ehPersonagem) {
                    String falaLimpa = fala.substring(index + 1).trim();
                    resultado.add(Map.entry(prefixoDaFala, falaLimpa));
                }
            }
        }
        return resultado;
    }

}
