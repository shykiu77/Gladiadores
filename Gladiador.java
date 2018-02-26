/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacewarriorsthunderfx;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author shikyu
 */
public class Gladiador extends Elemento implements Movel {

   

    private double ataque;
    private double defesa;
    private Bolsa bolsa;
    private boolean bot = false;
    private boolean mouse;
    
    public boolean isMouse() {
        return mouse;
    }

    public void setMouse(boolean mouse) {
        this.mouse = mouse;
    }

    public Bolsa getBolsa() {
        return bolsa;
    }
    public boolean isBot() {
        return bot;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
    }
    
    public Gladiador(int x, int y,int TILE_SIZE,boolean mouse,boolean bot) {
       
        this.mouse=mouse;
        this.bot=bot;
        bolsa = new Bolsa();

        setTranslateX(x * TILE_SIZE);
        setTranslateY(y * TILE_SIZE);

        setImg(new ImageView(new Image("imagens/Gladiador.jpg")));
        getImg().setFitHeight(TILE_SIZE);
        getImg().setFitWidth(TILE_SIZE);
        getChildren().add(getImg());
        getImg().setOnMouseClicked((MouseEvent event) -> {
            
        });
    }
    
    public Gladiador(){}

   

    public double getAtaque() {
        return ataque;
    }

    public void setAtaque(double ataque) {
        this.ataque = ataque;
    }

    public double getDefesa() {
        return defesa;
    }

    public void setDefesa(double defesa) {
        this.defesa = defesa;
    }

    public void atualizarAtaque() {
        this.ataque = getBolsa().somarAtaques();
    }

    public void atualizarDefesa() {
        this.defesa = getBolsa().somarDefesas();
    }

   
   
     
     
}
