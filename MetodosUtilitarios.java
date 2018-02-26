/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacewarriorsthunderfx;

/**
 *
 * @author shikyu
 */
public class MetodosUtilitarios {
     public static int[] desviarCruz(int linha_alvo, int coluna_alvo,int tamanho_mapa){
        int[] coordenada = new int[2];
        coordenada[0]=linha_alvo;
        coordenada[1]=coluna_alvo;
        int contagem=1;
        
        int[][] possibilidades = new int[5][];
        
        for(int i=0;i<5;i++)
            possibilidades[i]=new int[2];
        
        
        possibilidades[0][0]=linha_alvo;
        possibilidades[0][1]=coluna_alvo;
       
        if(linha_alvo>0){
            possibilidades[contagem][0]=linha_alvo-1;
            possibilidades[contagem][1]=coluna_alvo;
            contagem++;
        }
        
        if(linha_alvo<tamanho_mapa-1){
            possibilidades[contagem][0]=linha_alvo+1;
            possibilidades[contagem][1]=coluna_alvo;
            contagem++;
        }
        
          if(coluna_alvo<tamanho_mapa-1){
            possibilidades[contagem][0]=linha_alvo;
            possibilidades[contagem][1]=coluna_alvo+1;
            contagem++;
        }
        
        if(coluna_alvo>0){
            possibilidades[contagem][0]=linha_alvo;
            possibilidades[contagem][1]=coluna_alvo-1;
            contagem++;
        }  

          
        int  sorteio =  (int) (contagem *  Math.random());
        
        return possibilidades[sorteio];
    }
 
    

    
    public static int[] desviarQuadrado(int linha_alvo, int coluna_alvo,int tamanho_mapa){
        int[] coordenada = new int[2];
        coordenada[0]=linha_alvo;
        coordenada[1]=coluna_alvo;
        int contagem=1;
        
        int[][] possibilidades = new int[9][];
        
        for(int i=0;i<9;i++)
            possibilidades[i]=new int[2];
        
        
        possibilidades[0][0]=linha_alvo;
        possibilidades[0][1]=coluna_alvo;
       
        if(linha_alvo>0){
            possibilidades[contagem][0]=linha_alvo-1;
            possibilidades[contagem][1]=coluna_alvo;
            contagem++;
            if(coluna_alvo>0){
                possibilidades[contagem][0]=linha_alvo-1;
                possibilidades[contagem][1]=coluna_alvo-1;
                contagem++;
            }
        }
        
        if(linha_alvo<tamanho_mapa-1){
            possibilidades[contagem][0]=linha_alvo+1;
            possibilidades[contagem][1]=coluna_alvo;
            contagem++;
            if(coluna_alvo<tamanho_mapa-1){
                possibilidades[contagem][0]=linha_alvo+1;
                possibilidades[contagem][1]=coluna_alvo+1;
                contagem++;
            }
        }
        
          if(coluna_alvo<tamanho_mapa-1){
            possibilidades[contagem][0]=linha_alvo;
            possibilidades[contagem][1]=coluna_alvo+1;
            contagem++;
            if(linha_alvo>0){
                possibilidades[contagem][0]=linha_alvo-1;
                possibilidades[contagem][1]=coluna_alvo+1;
                contagem++;
            }
        }
        
        if(coluna_alvo>0){
            possibilidades[contagem][0]=linha_alvo;
            possibilidades[contagem][1]=coluna_alvo-1;
            contagem++;
            if(linha_alvo<tamanho_mapa-1){
                possibilidades[contagem][0]=linha_alvo+1;
                possibilidades[contagem][1]=coluna_alvo-1;
                contagem++;
            }
        }  
        
        
          
        int  sorteio =  (int) (contagem *  Math.random());
        return possibilidades[sorteio];
        
        
    }
}
