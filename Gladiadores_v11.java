/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacewarriorsthunderfx;

import static interfacewarriorsthunderfx.MetodosUtilitarios.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

public class Gladiadores_v11 extends Application {

    public void autoSIZE_MATRIZ() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        TILE_SIZE = dim.height / getDIMENSAO();
    }

    public int getTurno_atual() {
        return turno_atual;
    }

 
    public int getTILE_SIZE() {
        return TILE_SIZE;
    }

    public void setTILE_SIZE(int TILE_SIZE) {
        this.TILE_SIZE = TILE_SIZE;
    }

    public int getDIMENSAO() {
        return DIMENSAO;
    }

    public void setDIMENSAO(int DIMENSAO) {
        this.DIMENSAO = DIMENSAO;
    }

    private int TILE_SIZE;
    private int DIMENSAO;

    private Tile[][] board;
    private Group tileGroup = new Group();
    private Group elementosGroup = new Group();
    private Label lbl;

    

    private Tile jogador;
    private ArrayList<Gladiador> turnos = new ArrayList<>();
    private int turno_atual = 0;
    //apenas um dos dois verdade.

    private boolean DesvioCruz;
    private boolean DesvioQuadrado;

    private boolean jogavel = true;

    public void AtualizarLista(ArrayList<Gladiador> turnos) {
        for (int y = 0; y < getDIMENSAO(); y++) {
            for (int x = 0; x < getDIMENSAO(); x++) {
                if (board[x][y].getElemento() instanceof Movel) {
                    turnos.add((Gladiador) board[x][y].getElemento());
                }
            }
        }
        Collections.shuffle(turnos);
    }
    //percorre todo o arraylist jogavel

    public void AtualizarJogador() {

        for (int y = 0; y < getDIMENSAO(); y++) {
            for (int x = 0; x < getDIMENSAO(); x++) {
                if (turnos.get(getTurno_atual()).equals(board[x][y].getElemento())) {
                    this.jogador = board[x][y];

                }
            }
        }

    }

    public void start(Stage primaryStage, int[] vet) throws Exception {

        setDIMENSAO(vet[0]);
        autoSIZE_MATRIZ();

        Pane root = new Pane();
        //root.setStyle("-fx-background-color: red;");
        root.setPrefSize((getDIMENSAO() + 2) * getTILE_SIZE(), getDIMENSAO() * getTILE_SIZE());
        indicador = new IndicadorTurno(TILE_SIZE, DIMENSAO);

        root.getChildren().addAll(tileGroup, elementosGroup, indicador);

        board = new Tile[getDIMENSAO()][getDIMENSAO()];
        for (int y = 0; y < getDIMENSAO(); y++) {
            for (int x = 0; x < getDIMENSAO(); x++) {
                Tile tile = new Tile(x, y, getTILE_SIZE());

                board[x][y] = tile;

                this.DefinirEventoMouse(tile);
                tileGroup.getChildren().add(tile);

            }
        }

        CriarInterfaceTeclado(board, root, new Gladiador());
        CriarStatus(root);
        distribuir(vet[1], vet[2], vet[3], vet[4], board, root);

        AtualizarLista(turnos);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("classesCSS/darktheme.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);
        //pra que serve essas 3 linhas abaixo?
        ImageView imv = new ImageView(new Image("imagens/Mapa.jpg"));
        imv.setFitHeight(TILE_SIZE);
        imv.setFitWidth(TILE_SIZE);
        Bot();
        AtualizarJogador();

        FixIndicador(root);
        MoverIndicador();

    }

    public void distribuir(int num_gladiadores, int num_armadilhas, int num_armas, int num_escudos, Tile[][] board, Pane root) {
        ArrayList<int[]> possibilidades = Possibilidades(getDIMENSAO(), getDIMENSAO());
        for (int i = 0; i < num_gladiadores; i++) {
            int indice = (int) (possibilidades.size() * Math.random());

            Gladiador gladiador = new Gladiador(possibilidades.get(indice)[0], possibilidades.get(indice)[1], getTILE_SIZE(), true, false);

            board[possibilidades.get(indice)[0]][possibilidades.get(indice)[1]].setElemento(gladiador);
            elementosGroup.getChildren().add(gladiador);
            possibilidades.remove(indice);
            gladiador.getImg().setOnMouseClicked((MouseEvent event) -> {
                for (int a = 0; a < DIMENSAO; a++) {
                    for (int b = 0; b < DIMENSAO; b++) {
                        if (board[b][a].estaOcupado() && board[b][a].getElemento().equals(gladiador)) {
                            Tile alvo = board[b][a];

                            if (!turnos.get(turno_atual).isMouse()) {
                                return;
                            }
                            int[] posDesvio = alvo.getCoordenadas();
                            if (DesvioCruz) {
                                posDesvio = desviarCruz(alvo.getCoordenadas()[0], alvo.getCoordenadas()[1], DIMENSAO);
                            }
                            if (DesvioQuadrado) {
                                posDesvio = desviarCruz(alvo.getCoordenadas()[0], alvo.getCoordenadas()[1], DIMENSAO);
                            }
                            Agir(posDesvio[0], posDesvio[1]);
                        }
                    }
                }

            });
            gladiador.getImg().setOnMouseEntered((MouseEvent event) -> {
                DoubleStringConverter c = new DoubleStringConverter();
                ataqueGladiador.setText("Ataque: " + c.toString(gladiador.getAtaque()));
                defesaGladiador.setText("Defesa: " + c.toString(gladiador.getDefesa()));
                
                imvGladiador.setTranslateX(TILE_SIZE * DIMENSAO + 5);
                imvGladiador.setFitHeight(80);
                imvGladiador.setFitWidth(65);
                root.getChildren().add(imvGladiador);

            });
            gladiador.getImg().setOnMouseExited((MouseEvent event) -> {
                ataqueGladiador.setText("");
                defesaGladiador.setText("");
                root.getChildren().remove(imvGladiador);
            });
        }
        for (int i = 0; i < num_armadilhas; i++) {
            int indice = (int) (possibilidades.size() * Math.random());
            Armadilha a = new Armadilha(30, possibilidades.get(indice)[0], possibilidades.get(indice)[1], getTILE_SIZE());
            board[possibilidades.get(indice)[0]][possibilidades.get(indice)[1]].setElemento(a);
            elementosGroup.getChildren().add(a);
            possibilidades.remove(indice);
            a.getImg().setOnMouseClicked((MouseEvent event) -> {
                for (int u = 0; u < DIMENSAO; u++) {
                    for (int v = 0; v < DIMENSAO; v++) {
                        if (board[v][u].estaOcupado() && board[v][u].getElemento().equals(a)) {
                            Tile alvo = board[v][u];
                            if (!turnos.get(turno_atual).isMouse()) {
                                return;
                            }
                            int[] posDesvio = alvo.getCoordenadas();
                            if (DesvioCruz) {
                                posDesvio = desviarCruz(alvo.getCoordenadas()[0], alvo.getCoordenadas()[1], DIMENSAO);
                            }
                            if (DesvioQuadrado) {
                                posDesvio = desviarCruz(alvo.getCoordenadas()[0], alvo.getCoordenadas()[1], DIMENSAO);
                            }
                            Agir(posDesvio[0], posDesvio[1]);
                        }
                    }
                }
                ;
            });
            a.getImg().setOnMouseEntered((MouseEvent event) -> {
                DoubleStringConverter c = new DoubleStringConverter();
                forcaArmadilha.setText("Força: " + c.toString(a.getForca()));
                imvArmadilha.setTranslateX(TILE_SIZE * DIMENSAO  + 5);
                imvArmadilha.setFitHeight(65);
                imvArmadilha.setFitWidth(65);
                root.getChildren().add(imvArmadilha);

            });
            a.getImg().setOnMouseExited((MouseEvent event) -> {
                forcaArmadilha.setText("");
                root.getChildren().remove(imvArmadilha);
            });
        }
        for (int i = 0; i < num_armas; i++) {
            int indice = (int) (possibilidades.size() * Math.random());
            Arma a = new Arma(30, possibilidades.get(indice)[0], possibilidades.get(indice)[1], getTILE_SIZE());
            board[possibilidades.get(indice)[0]][possibilidades.get(indice)[1]].setElemento(a);
            elementosGroup.getChildren().add(a);
            possibilidades.remove(indice);
            a.getImg().setOnMouseClicked((MouseEvent event) -> {
                for (int u = 0; u < DIMENSAO; u++) {
                    for (int v = 0; v < DIMENSAO; v++) {
                        if (board[v][u].estaOcupado() && board[v][u].getElemento().equals(a)) {
                            Tile alvo = board[v][u];
                            if (!turnos.get(turno_atual).isMouse()) {
                                return;
                            }
                            int[] posDesvio = alvo.getCoordenadas();
                            if (DesvioCruz) {
                                posDesvio = desviarCruz(alvo.getCoordenadas()[0], alvo.getCoordenadas()[1], DIMENSAO);
                            }
                            if (DesvioQuadrado) {
                                posDesvio = desviarCruz(alvo.getCoordenadas()[0], alvo.getCoordenadas()[1], DIMENSAO);
                            }
                            Agir(posDesvio[0], posDesvio[1]);

                        }
                    }
                }

            });
            a.getImg().setOnMouseEntered((MouseEvent event) -> {
                DoubleStringConverter c = new DoubleStringConverter();
                ataqueArma.setText("Ataque: " + c.toString(a.getPoder()));
                imvArma.setTranslateX(TILE_SIZE * DIMENSAO + 5);
                imvArma.setFitHeight(65);
                imvArma.setFitWidth(65);
                root.getChildren().add(imvArma);

            });
            a.getImg().setOnMouseExited((MouseEvent event) -> {
                ataqueArma.setText("");
                root.getChildren().remove(imvArma);

            });
        }
        for (int i = 0; i < num_escudos; i++) {
            int indice = (int) (possibilidades.size() * Math.random());
            Escudo e = new Escudo(30, possibilidades.get(indice)[0], possibilidades.get(indice)[1], getTILE_SIZE());
            board[possibilidades.get(indice)[0]][possibilidades.get(indice)[1]].setElemento(e);
            elementosGroup.getChildren().add(e);
            possibilidades.remove(indice);
            e.getImg().setOnMouseClicked((MouseEvent event) -> {
                for (int u = 0; u < DIMENSAO; u++) {
                    for (int v = 0; v < DIMENSAO; v++) {
                        if (board[v][u].estaOcupado() && board[v][u].getElemento().equals(e)) {
                            Tile alvo = board[v][u];
                            if (!turnos.get(turno_atual).isMouse()) {
                                return;
                            }
                            int[] posDesvio = alvo.getCoordenadas();
                            if (DesvioCruz) {
                                posDesvio = desviarCruz(alvo.getCoordenadas()[0], alvo.getCoordenadas()[1], DIMENSAO);
                            }
                            if (DesvioQuadrado) {
                                posDesvio = desviarCruz(alvo.getCoordenadas()[0], alvo.getCoordenadas()[1], DIMENSAO);
                            }
                            Agir(posDesvio[0], posDesvio[1]);
                        }
                    }
                }

            });
            e.getImg().setOnMouseEntered((MouseEvent event) -> {
                DoubleStringConverter c = new DoubleStringConverter();
                defesaEscudo.setText("Defesa: " + c.toString(e.getProtecao()));
                imvEscudo.setTranslateX(TILE_SIZE * DIMENSAO + 5);
                imvEscudo.setFitHeight(65);
                imvEscudo.setFitWidth(65);
                root.getChildren().add(imvEscudo);
            });
            e.getImg().setOnMouseExited((MouseEvent event) -> {
                defesaEscudo.setText("");
                root.getChildren().remove(imvEscudo);
            });
        }

    }

    public ArrayList<int[]> Possibilidades(int num_linhas, int num_colunas) {
        ArrayList<int[]> possibilidades = new ArrayList<>();
        for (int i = 0; i < num_linhas; i++) {
            for (int j = 0; j < num_colunas; j++) {
                int[] coordenada = new int[2];
                coordenada[0] = i;
                coordenada[1] = j;
                possibilidades.add(coordenada);
            }
        }

        return possibilidades;

    }

    //parametro descecessário?
    public void CriarInterfaceTeclado(Tile[][] board, Pane root, Gladiador gladiador) {

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();

        TextField tf = new TextField();
        TextField tf2 = new TextField();
        Button btn = new Button();
        btn.setTranslateX(dim.width - 120);
        btn.setTranslateY(60);
        btn.setScaleY(0.8);
        btn.setGraphic(new ImageView(new Image("imagens/btnJOGAR.png")));

        tf.setTranslateX(dim.width - 150);
        tf2.setTranslateX(dim.width - 150);
        tf2.setTranslateY(30);

        root.getChildren().addAll(tf, tf2, btn);

        btn.setOnAction((ActionEvent event) -> {

            int a = Integer.parseInt(tf.getText());
            int b = Integer.parseInt(tf2.getText());
            int[] posDesvio = new int[2];
            posDesvio[0] = a;
            posDesvio[1] = b;
            if (DesvioCruz) {
                posDesvio = desviarCruz(a, b, DIMENSAO);
            }
            if (DesvioQuadrado) {
                posDesvio = desviarCruz(a, b, DIMENSAO);
            }
            Agir(posDesvio[0], posDesvio[1]);

        });
    }

    public void mover(Tile board, Tile jogador, int TILE_SIZE) {

        board.setElemento(jogador.getElemento());
        board.getElemento().setTranslateX(board.getCoordenadas()[0] * TILE_SIZE);
        board.getElemento().setTranslateY(board.getCoordenadas()[1] * TILE_SIZE);
        jogador.setElemento(null);
    }

    public void DefinirEventoMouse(Tile alvo) {
        alvo.setOnMouseClicked((MouseEvent event) -> {
            if (!turnos.get(turno_atual).isMouse()) {
                return;
            }
            int[] posDesvio = alvo.getCoordenadas();
            if (DesvioCruz) {
                posDesvio = desviarCruz(alvo.getCoordenadas()[0], alvo.getCoordenadas()[1], DIMENSAO);
            }
            if (DesvioQuadrado) {
                posDesvio = desviarCruz(alvo.getCoordenadas()[0], alvo.getCoordenadas()[1], DIMENSAO);
            }
            Agir(posDesvio[0], posDesvio[1]);
        });
    }

    public void Bot() {
        
        
        if (turno_atual >= turnos.size()) {
            turno_atual = 0;
            Collections.shuffle(turnos);
        }

        if (!turnos.get(turno_atual).isBot()) {
            return;
        }

        ArrayList<int[]> possibilidades = Possibilidades(DIMENSAO, DIMENSAO);

        int a = possibilidades.get((int) (Math.random() * DIMENSAO))[1];
        int b = possibilidades.get((int) (Math.random() * DIMENSAO))[1];

        int[] posDesvio = new int[2];
        posDesvio[0] = a;
        posDesvio[1] = b;
        System.out.println(posDesvio[0] + ", " + posDesvio[1]);
        if (DesvioCruz) {
            posDesvio = desviarCruz(a, b, DIMENSAO);
        }
        if (DesvioQuadrado) {
            posDesvio = desviarCruz(a, b, DIMENSAO);
        }
        System.out.println(posDesvio[0] + ", " + posDesvio[1]);
        Agir(posDesvio[0], posDesvio[1]);

        if (turnos.size() > 1) {
            Bot();
        }

    }

    public void passarTurno() {
        turno_atual++;
        if (turno_atual >= turnos.size()) {
            turno_atual = 0;
            Collections.shuffle(turnos);

        }
        AtualizarJogador();
        MoverIndicador();
    }

    public void Agir(int a, int b) {

        if (turnos.size() <= 1) {
            jogavel = false;
        }
        if (!jogavel) {
            return;
        }

        AtualizarJogador();

        if (!board[a][b].estaOcupado()) {

            board[a][b].setElemento(jogador.getElemento());
            board[a][b].getElemento().setTranslateX(board[a][b].getCoordenadas()[0] * getTILE_SIZE());
            board[a][b].getElemento().setTranslateY(board[a][b].getCoordenadas()[1] * getTILE_SIZE());
            jogador.setElemento(null);
            passarTurno();
            Bot();
            return;

        }

        if (board[a][b].getElemento() instanceof Arma) {
            System.out.println("arma pega");
            board[a][b].getElemento().getChildren().remove(0);
            jogador.interagir((Arma) board[a][b].getElemento(), (Gladiador) jogador.getElemento());

            mover(board[a][b], jogador, getTILE_SIZE());
            passarTurno();
            Bot();
            return;

        }
        if (board[a][b].getElemento() instanceof Escudo) {
            board[a][b].getElemento().getChildren().remove(0);
            jogador.interagir((Escudo) board[a][b].getElemento(), (Gladiador) jogador.getElemento());

            mover(board[a][b], jogador, getTILE_SIZE());
            passarTurno();
            Bot();
            return;

        }

        if (board[a][b].getElemento() instanceof Armadilha) {

            if (jogador.interagir((Armadilha) board[a][b].getElemento(), (Gladiador) jogador.getElemento())) {
                turnos.remove((Gladiador) jogador.getElemento());
                jogador.getElemento().getChildren().remove(0);
                jogador.setElemento(null);
                System.out.println("e morreu pra armadilha");

                Bot();
                return;

            } else {
                board[a][b].getElemento().getChildren().remove(0);
                mover(board[a][b], jogador, getTILE_SIZE());
                passarTurno();
                Bot();
                return;
            }
        }

        //passa o turno se voltar para o mesmo lugar
        if (jogador.getElemento().equals(board[a][b].getElemento())) {
            passarTurno();
            return;
        }

        if (board[a][b].getElemento() instanceof Gladiador) {
            //pela esquerda da lista
            for (int i = 0; i <= getTurno_atual(); i++) {
                if (turnos.get(i).equals(board[a][b].getElemento()) && turno_atual > 0) {
                    turno_atual--;
                }
            }

            if (jogador.interagir((Gladiador) jogador.getElemento(), (Gladiador) board[a][b].getElemento()) == 0) {

                turnos.remove((Gladiador) board[a][b].getElemento());
                //ataque movel maior que ataque fixo
                board[a][b].getElemento().getChildren().remove(0);
                mover(board[a][b], jogador, getTILE_SIZE());
                System.out.println("jogador ganhou");
                passarTurno();
                Bot();
                return;

            } else {
                turnos.remove((Gladiador) jogador.getElemento());
                jogador.getElemento().getChildren().remove(0);
                jogador.setElemento(null);
                System.out.println("e morreu pra gladiador");
                passarTurno();
                Bot();
                return;
            }

        }
        return;

    }

    // o tamanho da fonte pode ser setado em setFont, deixar o translate y como +- tamanho da fonte deixa tudo alinhado.
    public void CriarStatus(Pane root) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();

        imvGladiador = new ImageView(new Image("imagens/Gladiador.png"));
        ataqueGladiador = new Text(TILE_SIZE * DIMENSAO + 80, 20 , "");
        ataqueGladiador.setFont(Font.font("Verdana", 15));

        defesaGladiador = new Text(TILE_SIZE * DIMENSAO + 80, 40 , "");
        defesaGladiador.setFont(Font.font("Verdana", 15));

        imvArmadilha = new ImageView(new Image("imagens/Armadilha.png"));
        forcaArmadilha = new Text(TILE_SIZE * DIMENSAO + 80, 32.5, "");
        forcaArmadilha.setFont(Font.font("Verdana", 15));

        imvArma = new ImageView(new Image("imagens/Arma.png"));
        ataqueArma = new Text(TILE_SIZE * DIMENSAO + 80, 32.5 , "");
        ataqueArma.setFont(Font.font("Verdana", 15));

        imvEscudo = new ImageView(new Image("imagens/Escudo.png"));
        defesaEscudo = new Text(TILE_SIZE * DIMENSAO + 80, 32.5 , "");
        defesaEscudo.setFont(Font.font("Verdana", 15));

        root.getChildren().addAll(ataqueGladiador, defesaGladiador, forcaArmadilha, ataqueArma, defesaEscudo);
    }

    public Text defesaGladiador;
    public Text ataqueGladiador;
    public Text forcaArmadilha;
    public Text ataqueArma;
    public Text defesaEscudo;

    public ImageView imvGladiador;
    public ImageView imvArmadilha;
    public ImageView imvArma;
    public ImageView imvEscudo;

    IndicadorTurno indicador;

    public void FixIndicador(Pane root) {
        indicador.setOnMouseEntered((MouseEvent event) -> {
            DoubleStringConverter c = new DoubleStringConverter();
            //o indicador vai estar sempre no jogador do turno atual.
            ataqueGladiador.setText("Ataque: " + c.toString(turnos.get(turno_atual).getAtaque()));
            defesaGladiador.setText("Defesa: " + c.toString(turnos.get(turno_atual).getDefesa()));
            imvGladiador.setTranslateX(TILE_SIZE * DIMENSAO + 5);
            imvGladiador.setFitHeight(80);
            imvGladiador.setFitWidth(65);
            root.getChildren().add(imvGladiador);

        });
        indicador.setOnMouseExited((MouseEvent event) -> {
            ataqueGladiador.setText("");
            defesaGladiador.setText("");
            root.getChildren().remove(imvGladiador);
        });

    }

    public void MoverIndicador() {
        indicador.mover(jogador.getCoordenadas(), TILE_SIZE);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

}
