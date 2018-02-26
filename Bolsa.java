/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacewarriorsthunderfx;

import java.util.ArrayList;



public class Bolsa  {

    
   
    private ArrayList<Arma> lista_armas;
    private ArrayList<Escudo> lista_escudos;
    
    public Bolsa(){
        lista_armas = new ArrayList<>();
        lista_escudos = new ArrayList<>();    
    }
    
     public ArrayList<Arma> getLista_armas() {
        return lista_armas;
    }

    
    public ArrayList<Escudo> getLista_escudos() {
        return lista_escudos;
    }
    
    
    public void AdicionarArma(Arma objeto){
        getLista_armas().add(objeto);
    }
    
    public void AdicionarEscudo(Escudo objeto){
        getLista_escudos().add(objeto);
    }
    
   
    public void RemoverEscudo(){
        double maior = lista_escudos.get(0).getProtecao();
        int indiceEscudoMaiorProt=0;
        
        for(int i=0,j=i+1;i<lista_escudos.size()-1;i++,j++){
            if(maior < lista_escudos.get(j).getProtecao()){
                maior = lista_escudos.get(j).getProtecao();
                indiceEscudoMaiorProt = lista_escudos.indexOf(lista_escudos.get(j));
            }
        }
        System.out.println(indiceEscudoMaiorProt);
        for( Escudo escudo : lista_escudos){
            System.out.println(escudo.getProtecao());
        }
        lista_escudos.remove(indiceEscudoMaiorProt);
        for( Escudo escudo : lista_escudos){
            System.out.println(escudo.getProtecao());
        }
    }
    
    
    public double somarAtaques(){
        double soma=0;
        for(int i=0;i<lista_armas.size();i++)
            soma+=lista_armas.get(i).getPoder();
        
        return soma;
    }
    
    public double somarDefesas(){
        double soma=0;
        for(int i=0;i<lista_escudos.size();i++)
            soma+=lista_escudos.get(i).getProtecao();
        
        return soma;
    }
   
    
}
