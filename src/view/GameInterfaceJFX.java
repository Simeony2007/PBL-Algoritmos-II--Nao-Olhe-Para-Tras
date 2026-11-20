package src.view;

import src.controller.ControllerFX;
import src.model.Observer;
import src.model.service.logic.Npc;
import src.model.service.logic.Orfeu;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.animation.SequentialTransition;

public class GameInterfaceJFX implements Observer {

    private Scene scene;
    private ControllerFX fxControl; // Acho que dá pra usar como Static - Simeony
    private Label nomePersonagem;
    private Label falaPersonagem;
    private HBox hBox;
    private VBox choicesVBox;
    private VBox notificacoesVBox;
    private BorderPane bpane;
    private SequentialTransition sequencia;
    private ArrayList<Map.Entry<String, String>> dialogues;
    private Timeline timelineFala;
    private int indexDialogue = 0;

    @Override
    public void modelUpdated(Orfeu orfeu, ArrayList<Npc> npcs, Orfeu oldOrfeu, ArrayList<Npc> oldNpcs) {
        for (int i = 0; i < npcs.size(); i++) {
            int diffAff = npcs.get(i).getAffinity() - oldNpcs.get(i).getAffinity();
            if (diffAff != 0) {
                showNotification(String.format("%s: %+d de afinidade", npcs.get(i).getName(), diffAff));
            }
        }

        int quantAngry = orfeu.getAngry() - oldOrfeu.getAngry();
        int quantObol = orfeu.getObol() - oldOrfeu.getObol();
        int quantLove = orfeu.getLove() - oldOrfeu.getLove();
        int quantSadness = orfeu.getSadness() - oldOrfeu.getSadness();

        if (quantAngry != 0) {
            showNotification(String.format("%+d de raiva para Orfeu", quantAngry));
        }
        if (quantObol != 0) {
            showNotification(String.format("%+d óbolo para Orfeu", quantObol));
        }
        if (quantLove != 0) {
            showNotification(String.format("%+d de amor para Orfeu", quantLove));
        }
        if (quantSadness != 0) {
            showNotification(String.format("%+d de tristeza para Orfeu", quantSadness));
        }

        System.out.println("OBSERVER!");

    }

    @Override
    public void saveWasMade() {
        showNotification("Jogo salvo!");
    }

    public void showNotification(String mensagem) {
        Label aviso = new Label(mensagem);
        aviso.setFont(new Font("Arial", 16));
        aviso.setTextFill(Color.WHITE);
        aviso.setPadding(new Insets(8, 15, 8, 15));
        aviso.setBackground(new Background(new BackgroundFill(
                Color.rgb(20, 20, 28, 0.90),
                new CornerRadii(8),
                Insets.EMPTY)));
        aviso.setBorder(new Border(new BorderStroke(
                Color.rgb(158, 131, 9),
                BorderStrokeStyle.SOLID,
                new CornerRadii(8),
                new BorderWidths(1.5))));

        notificacoesVBox.getChildren().add(aviso);
        notificacoesVBox.setVisible(true);

        PauseTransition timer = new PauseTransition(Duration.seconds(2.5));
        timer.setOnFinished(e -> {
            notificacoesVBox.getChildren().remove(aviso);
            if (notificacoesVBox.getChildren().isEmpty()) {
                notificacoesVBox.setVisible(false);
            }
        });
        timer.play();
    }

    public void setController(ControllerFX ctrl) {
        fxControl = ctrl;
    }

    public GameInterfaceJFX() {
        StackPane stackPane = new StackPane();
        VBox vBox = new VBox();
        bpane = new BorderPane();
        hBox = new HBox();
        this.choicesVBox = new VBox();

        choicesVBox.setVisible(false);
        choicesVBox.setId("PainelDeEscolhas");
        choicesVBox.setAlignment(Pos.CENTER);
        choicesVBox.setSpacing(12);

        nomePersonagem = new Label();
        falaPersonagem = new Label();

        nomePersonagem.setFont(new Font("Arial", 20));
        nomePersonagem.setTextFill(Color.WHITE);
        nomePersonagem.setAlignment(Pos.CENTER_LEFT);
        nomePersonagem.setId("NomeDoPersonagem");

        falaPersonagem.setFont(new Font("Arial", 20));
        falaPersonagem.setTextFill(Color.WHITE);
        falaPersonagem.setWrapText(true);
        falaPersonagem.setAlignment(Pos.TOP_LEFT);
        falaPersonagem.setId("FalaDoPersonagem");

        double alturaNome = 38;
        double alturaFala = 122;
        double larguraAbaNome = 240;

        nomePersonagem.setMinHeight(alturaNome);
        nomePersonagem.setPrefHeight(alturaNome);
        nomePersonagem.setMaxHeight(alturaNome);
        nomePersonagem.setPadding(new Insets(0, 15, 0, 15));

        falaPersonagem.setMinHeight(alturaFala);
        falaPersonagem.setPrefHeight(alturaFala);
        falaPersonagem.setMaxHeight(alturaFala);
        falaPersonagem.setMaxWidth(Double.MAX_VALUE);
        falaPersonagem.setPadding(new Insets(12, 20, 12, 20));

        Polygon poligonoCaixa = new Polygon();
        poligonoCaixa.setFill(Color.rgb(20, 20, 28, 0.88));
        poligonoCaixa.setStroke(Color.rgb(158, 131, 9));
        poligonoCaixa.setStrokeWidth(2.0);
        poligonoCaixa.setManaged(false);

        Runnable atualizarPoligono = () -> {
            double w = vBox.getWidth();
            double hTotal = alturaNome + alturaFala;
            String nomeAtual = nomePersonagem.getText();
            boolean temNome = nomeAtual != null && !nomeAtual.trim().isEmpty();

            if (temNome) {
                poligonoCaixa.getPoints().setAll(
                        0.0, 0.0,
                        larguraAbaNome, 0.0,
                        larguraAbaNome + 20, alturaNome,
                        w, alturaNome,
                        w, hTotal,
                        0.0, hTotal);
            } else {
                poligonoCaixa.getPoints().setAll(
                        0.0, alturaNome,
                        w, alturaNome,
                        w, hTotal,
                        0.0, hTotal);
            }
        };

        vBox.widthProperty().addListener((obs, oldVal, newVal) -> atualizarPoligono.run());
        nomePersonagem.textProperty().addListener((obs, oldVal, newVal) -> atualizarPoligono.run());

        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.setMinHeight(alturaNome + alturaFala);
        vBox.setPrefHeight(alturaNome + alturaFala);
        vBox.setMaxHeight(alturaNome + alturaFala);
        vBox.setPadding(Insets.EMPTY);
        vBox.setSpacing(0);

        hBox.setAlignment(Pos.BOTTOM_CENTER);

        VBox miniMenu = miniMenu();
        miniMenu.setAlignment(Pos.CENTER);
        miniMenu.setVisible(false);

        StackPane containerDialogo = new StackPane(poligonoCaixa, vBox);

        containerDialogo.setAlignment(Pos.TOP_LEFT);
        BorderPane.setMargin(containerDialogo, new Insets(0, 15, 15, 15));

        vBox.getChildren().addAll(nomePersonagem, falaPersonagem);

        Button btnMiniMenu = new Button("☰");

        BorderPane.setAlignment(btnMiniMenu, Pos.TOP_RIGHT);
        BorderPane.setMargin(btnMiniMenu, new Insets(15, 15, 0, 0));

        btnMiniMenu.setAlignment(Pos.CENTER);
        btnMiniMenu.setOnAction(e -> {
            if (miniMenu.isVisible()) {
                miniMenu.setVisible(false);
                openedMenu();
            } else {
                miniMenu.setVisible(true);
                openedMenu();
            }
        });

        bpane.setOnMouseClicked(e -> {
            if (!miniMenu.isVisible() && !choicesVBox.isVisible()) {
                nextDialogue();
            }
        });

        notificacoesVBox = new VBox(8); // 8px de espaço entre cada notificação empilhada
        notificacoesVBox.setAlignment(Pos.TOP_LEFT);
        notificacoesVBox.setMouseTransparent(true); // Não bloqueia cliques na tela do jogo
        notificacoesVBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        notificacoesVBox.setVisible(false);

        stackPane.getChildren().addAll(hBox, choicesVBox, notificacoesVBox, miniMenu);
        StackPane.setAlignment(notificacoesVBox, Pos.TOP_LEFT);
        StackPane.setMargin(notificacoesVBox, new Insets(15));

        bpane.setTop(btnMiniMenu);
        bpane.setCenter(stackPane);
        bpane.setBottom(containerDialogo);

        scene = new Scene(bpane);
    }

    public void openedMenu() {
        if (choicesVBox.isDisabled()) {
            choicesVBox.setDisable(false);
            if (timelineFala != null && timelineFala.getStatus() == Animation.Status.PAUSED) {
                timelineFala.play();
            }
        } else {
            choicesVBox.setDisable(true);
            if (timelineFala != null && timelineFala.getStatus() == Animation.Status.RUNNING) {
                timelineFala.pause();
            }
        }
    }

    public Scene gameScene() {
        return scene;
    }

    public void showDialogues(ArrayList<Map.Entry<String, String>> dialogos, int speed) {
        this.dialogues = dialogos;
        indexDialogue = 0;
        nextDialogue();
    }

    public void nextDialogue() {
        if (dialogues == null)
            return;

        // 1. Se a fala atual ainda está digitando, completa ela imediatamente
        if (timelineFala != null && timelineFala.getStatus() == javafx.animation.Animation.Status.RUNNING) {
            timelineFala.stop();
            // indexDialogo já foi incrementado quando essa fala começou, por isso usamos
            // (indexDialogo - 1)
            falaPersonagem.setText(dialogues.get(indexDialogue - 1).getValue());
            return;
        }

        // 2. Se já passou do último elemento do ArrayList, avisa o controller
        if (indexDialogue >= dialogues.size()) {
            fxControl.endedDialogues();
            return;
        }

        // 3. Pega o elemento atual da lista e avança o índice
        Map.Entry<String, String> atual = dialogues.get(indexDialogue);
        indexDialogue++;

        String nome = atual.getKey();
        String fala = atual.getValue();

        nomePersonagem.setText(nome);
        falaPersonagem.setText("");

        // Carrega o sprite do personagem
        hBox.getChildren().clear();
        if (nome != null && !nome.isEmpty()) {
            File arquivoSprite = new File("assets/sprites/character/" + nome + ".png");
            if (arquivoSprite.exists()) {
                try (InputStream is = new FileInputStream(arquivoSprite)) {
                    ImageView iv = new ImageView(new Image(is));
                    iv.setPreserveRatio(true);
                    iv.setFitHeight(350);
                    hBox.getChildren().add(iv);
                } catch (Exception ignored) {
                }
            }
        }

        final int[] i = { 0 };
        timelineFala = new Timeline(new KeyFrame(Duration.millis(15), e -> {
            if (i[0] < fala.length()) {
                falaPersonagem.setText(fala.substring(0, ++i[0]));
            }
        }));
        timelineFala.setCycleCount(fala.length());
        timelineFala.play();
    }

    // public void showDialogues(ArrayList<Map.Entry<String, String>> dialogos, int
    // speed) {
    // if (sequencia != null) {
    // sequencia.stop();
    // }
    // sequencia = new SequentialTransition();
    // for (Map.Entry<String, String> dialogue : dialogos) {
    // String nome = dialogue.getKey();
    // String fala = dialogue.getValue();

    // ImageView spriteView = null;
    // if (nome != null && !nome.isEmpty()) {
    // File arquivoSprite = new File("assets/sprites/character/" + nome + ".png");
    // if (arquivoSprite.exists()) {
    // try (InputStream is = new FileInputStream(arquivoSprite)) {
    // Image image = new Image(is);
    // spriteView = new ImageView(image);
    // spriteView.setPreserveRatio(true);
    // spriteView.setFitHeight(350); // Ajuste a altura do sprite conforme sua tela
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    // }

    // final ImageView imagemFinal = spriteView;

    // final int[] contadorLetras = { 0 };

    // KeyFrame frame = new KeyFrame(Duration.millis(1), evento -> {
    // if (contadorLetras[0] == 0) {
    // nomePersonagem.setText(nome);
    // hBox.getChildren().clear();
    // if (imagemFinal != null) {
    // hBox.getChildren().add(imagemFinal);
    // }
    // }
    // if (contadorLetras[0] < fala.length()) {
    // contadorLetras[0]++;
    // falaPersonagem.setText(fala.substring(0, contadorLetras[0]));
    // }
    // });

    // Timeline timelineFala = new Timeline(frame);
    // timelineFala.setCycleCount(fala.length());

    // sequencia.getChildren().addAll(timelineFala, new
    // PauseTransition(Duration.seconds(0.5)));
    // }
    // sequencia.setOnFinished(e -> fxControl.endedDialogues());
    // sequencia.play();
    // }

    public void showEndGame() {
        // TODO
    }

    public void showChoicesOptions(ArrayList<String> escolhas) {
        ArrayList<Button> choicesList = new ArrayList<>();

        choicesVBox.getChildren().clear();

        for (String str : escolhas) {
            Button nButton = new Button(str);
            choicesList.add(nButton);
        }

        choicesVBox.getChildren().addAll(choicesList);
        choicesVBox.setVisible(true);

        for (Button cButton : choicesList) {
            cButton.setOnAction(e -> {
                choicesVBox.setVisible(false);
                fxControl.choicedOption(choicesList.indexOf(cButton));
            });
        }

    }

    public void setChapterBackground(String nomeArquivoFundo) {
        File arquivoFundo = new File("assets/sprites/background/" + nomeArquivoFundo);
        if (arquivoFundo.exists()) {
            // Converte o caminho do Windows (C:\...) para formato URI válido no CSS do
            // JavaFX (file:/C:/...)
            String uri = arquivoFundo.toURI().toString();
            bpane.setStyle(
                    "-fx-background-image: url('" + uri + "'); " +
                            "-fx-background-size: cover; " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: no-repeat;");
        }
    }

    public VBox miniMenu() {
        VBox miniMenuVBox = new VBox();
        Button btnStatus = new Button("Status");
        Button btnConfig = new Button("Configurações");
        Button btnSalvar = new Button("Salvar");
        Button btnSair = new Button("Retornar ao menu");

        // Deixa todos os botões com a mesma largura dentro da caixa
        double larguraBotoes = 200;
        for (Button btn : new Button[] { btnStatus, btnConfig, btnSalvar, btnSair }) {
            btn.setPrefWidth(larguraBotoes);
            btn.setMaxWidth(Double.MAX_VALUE);
        }

        miniMenuVBox.getChildren().addAll(btnStatus, btnConfig, btnSalvar, btnSair);
        miniMenuVBox.setSpacing(20);
        miniMenuVBox.setAlignment(Pos.CENTER);
        miniMenuVBox.setPadding(new Insets(30));

        // 1. Impede o VBox de esticar para a tela inteira (fica apenas do tamanho dos
        // botões + padding)
        miniMenuVBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        // 2. Desenha o fundo quadrado/retangular e a borda diretamente no VBox
        miniMenuVBox.setBackground(new Background(new BackgroundFill(
                Color.rgb(20, 20, 28, 0.92), // Cor de fundo escura
                new CornerRadii(12), // Cantos arredondados (use CornerRadii.EMPTY para quina reta)
                Insets.EMPTY)));
        miniMenuVBox.setBorder(new Border(new BorderStroke(
                Color.rgb(158, 131, 9), // Mesma borda dourada da caixa de diálogo
                BorderStrokeStyle.SOLID,
                new CornerRadii(12),
                new BorderWidths(2))));
        return miniMenuVBox;
    }

    public void showEndChapter() {
        // TODO
    }
}
