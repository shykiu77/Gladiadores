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
public class Arma extends Elemento {

    private double poder;

    public Arma(double poder, int x, int y, int TILE_SIZE) {
        this.poder = poder;

        setTranslateX(x * TILE_SIZE);
        setTranslateY(y * TILE_SIZE);

        setImg(new ImageView(new Image("imagens/Arma.jpg")));
        getImg().setFitHeight(TILE_SIZE);
        getImg().setFitWidth(TILE_SIZE);
        getChildren().add(getImg());
    }

    public double getPoder() {
        return this.poder;
    }

    @Override
    public String toString() {
        return "sou uma arma";
    }

}
