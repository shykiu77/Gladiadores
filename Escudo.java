/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacewarriorsthunderfx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author shikyu
 */
public class Escudo extends Elemento {

    private double protecao;

    public void setProtecao(double protecao) {
        this.protecao = protecao;
    }

    public double getProtecao() {
        return protecao;
    }

    public Escudo(double protecao, int x, int y,int TILE_SIZE) {
        this.protecao = protecao;

        setTranslateX(x * TILE_SIZE);
        setTranslateY(y * TILE_SIZE);

        setImg(new ImageView(new Image("imagens/Escudo.jpg")));
        getImg().setFitHeight(TILE_SIZE);
        getImg().setFitWidth(TILE_SIZE);
        getChildren().add(getImg());
    }

    public String toString() {
        return "sou um escudo";
    }

}
