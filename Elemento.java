/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacewarriorsthunderfx;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author shikyu
 */
public abstract class Elemento extends Pane {

    private ImageView img;

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }
}
