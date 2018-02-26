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
public class Armadilha extends Elemento {

    private double forca;

    public void setForca(double forca) {
        this.forca = forca;
    }

    public double getForca() {
        return this.forca;
    }

    public Armadilha(double forca, int x, int y, int TILE_SIZE) {
        this.forca = forca;

        setTranslateX(x * TILE_SIZE);
        setTranslateY(y * TILE_SIZE);

        setImg(new ImageView(new Image("imagens/Armadilha.jpg")));
        getImg().setFitHeight(TILE_SIZE);
        getImg().setFitWidth(TILE_SIZE);
        getChildren().add(getImg());
    }

    public String toString() {
        return "sou uma trap";
    }

}
