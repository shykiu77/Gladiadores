/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacewarriorsthunderfx;

import static interfacewarriorsthunderfx.MetodosUtilitarios.desviarCruz;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author shikyu
 */
public class IndicadorTurno extends Rectangle {

    private int coordenadas[] = new int[2];

    public int[] getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(int[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public IndicadorTurno(int TILE_SIZE, int DIMENSAO) {
        this.setWidth(TILE_SIZE);
        this.setHeight(TILE_SIZE);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.GOLD);
        this.setStrokeType(StrokeType.INSIDE);
        this.setStrokeWidth(DIMENSAO / 2);

    }

    public void mover(int[] coordenadas, int TILE_SIZE) {

        setCoordenadas(coordenadas);
        this.setTranslateX(TILE_SIZE * coordenadas[0]);
        this.setTranslateY(TILE_SIZE * coordenadas[1]);
       

    }
}
