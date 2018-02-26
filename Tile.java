package interfacewarriorsthunderfx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Tile extends Pane {
    
    public ImageView imv;

    public int[] getCoordenadas() {
        return coordenadas;
    }

    private Elemento elemento;
    private int[] coordenadas = new int[2];

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    public boolean estaOcupado() {
        return elemento != null;
    }

    public void interagir(Arma arma, Gladiador gladiador) {
        gladiador.getBolsa().AdicionarArma(arma);
        gladiador.atualizarAtaque();
    }

    public void interagir(Escudo escudo, Gladiador gladiador) {
        gladiador.getBolsa().AdicionarEscudo(escudo);
        gladiador.atualizarDefesa();
    }

    public boolean interagir(Armadilha armadilha, Gladiador gladiador) {
        if (armadilha.getForca() > gladiador.getDefesa()) {
            return true;
        } else {
            gladiador.getBolsa().RemoverEscudo();
            gladiador.atualizarDefesa();
            return false;
        }
    }

    public int interagir(Gladiador gladiador_movel, Gladiador gladiador_fixo) {
        if (gladiador_movel.getAtaque() > gladiador_fixo.getAtaque()) {
            return 0;
        }

        if (gladiador_movel.getAtaque() < gladiador_fixo.getAtaque()) {
            return 1;
        } else {
            return (int) (2 * Math.random());
        }

    }

    public Tile(int x, int y, int TILE_SIZE) {
        coordenadas[0] = x;
        coordenadas[1] = y;
        Rectangle border = new Rectangle(TILE_SIZE, TILE_SIZE);

        imv = new ImageView(new Image("imagens/Mapa.jpg"));
        imv.setFitHeight(TILE_SIZE);
        imv.setFitWidth(TILE_SIZE);
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);
        setPrefSize(TILE_SIZE, TILE_SIZE);
        setTranslateX(x * TILE_SIZE);
        setTranslateY(y * TILE_SIZE);

        getChildren().addAll(border,imv);

    }

}
