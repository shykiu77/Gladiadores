package interfacewarriorsthunderfx;

import java.io.*;
import java.util.*;
/**
 *
 * @author Cristiano Lima Oliveira
 */
public class Arquivo {
    /* Vai fazer a leitura dos dados usando como parâmetro a localização do arquivo e retornará
    * um ArrayList contendo em cada índice os dados de cada linha do arquivo.
    */
    public ArrayList<String> leituraDeAquivo(String enderecoArquivo){
        ArrayList<String> conteudoArquivo = new ArrayList();
        String conteudoLinhas;
        try{
            FileReader arquivo = new FileReader(enderecoArquivo);
            BufferedReader leituraArquivo = new BufferedReader(arquivo);
            String linhasArquivo;
            try{
                linhasArquivo = leituraArquivo.readLine();
                conteudoLinhas = linhasArquivo;
                conteudoArquivo.add(conteudoLinhas);
                while(linhasArquivo!=null){
                    linhasArquivo = leituraArquivo.readLine();
                    conteudoArquivo.add(linhasArquivo);
                }
                arquivo.close();
            } catch(IOException io){} 
        }catch(FileNotFoundException arquivoNaoEncontrado){}
        return conteudoArquivo;
    }
    /* Escreverá no arquivo usando como parâmetro o endereço do arquivo onde serão escritos os dados
    * e um array contendo os dados, ele escreverá no arquivo o conteúdo de cada índice do array
    * em uma linha.
    */
    public boolean escreverArquivo(String enderecoArquivo,ArrayList<String> conteudoArquivo){
        try{
            try (FileWriter arquivo = new FileWriter(enderecoArquivo)) {
                PrintWriter gravarArquivo = new PrintWriter(arquivo);
                for(int i=0;i<conteudoArquivo.size();i++){
                    gravarArquivo.append(conteudoArquivo.get(i) + "\r\n");
                }
            }
            return true;
        } catch(IOException excecaoNaGravacao){
            return false;
        }
    }
    
    public boolean escreverArquivoNomesSaves(String enderecoArquivo,String nome){
        try{
            try (FileWriter arquivo = new FileWriter(enderecoArquivo,true)) {
                PrintWriter gravarArquivo = new PrintWriter(arquivo);
                gravarArquivo.append(nome + "\r\n");
            }
            return true;
        } catch(IOException excecaoNaGravacao){
            return false;
        }
    }
    
    public boolean escreverArquivoEscolha(String enderecoArquivo,String nome){
        try{
            try (FileWriter arquivo = new FileWriter(enderecoArquivo)) {
                PrintWriter gravarArquivo = new PrintWriter(arquivo);
                gravarArquivo.append(nome + "\r\n");
            }
            return true;
        } catch(IOException excecaoNaGravacao){
            return false;
        }
    }
    
    public boolean escreverArquivoModoDeJogo(String endereco,ArrayList<String> conteudoArquivo){
        try{
            try (FileWriter arquivo = new FileWriter("savesmododejogo/" + endereco,true)) {
                PrintWriter gravarArquivo = new PrintWriter(arquivo);
                for(int i=0;i<conteudoArquivo.size();i++){
                    gravarArquivo.append(conteudoArquivo.get(i) + "\r\n");
                }
            }
            return true;
        } catch(IOException excecaoNaGravacao){
            return false;
        }
    }
    
    public ArrayList<String> leituraDeAquivoConfig(String enderecoArquivo){
        ArrayList<String> conteudoArquivo = new ArrayList();
        String conteudoLinhas;
        try{
            FileReader arquivo = new FileReader("savesconfiguracoes/"+enderecoArquivo);
            BufferedReader leituraArquivo = new BufferedReader(arquivo);
            String linhasArquivo;
            try{
                linhasArquivo = leituraArquivo.readLine();
                conteudoLinhas = linhasArquivo;
                conteudoArquivo.add(conteudoLinhas);
                while(linhasArquivo!=null){
                    linhasArquivo = leituraArquivo.readLine();
                    conteudoArquivo.add(linhasArquivo);
                }
                arquivo.close();
            } catch(IOException io){} 
        }catch(FileNotFoundException arquivoNaoEncontrado){}
        return conteudoArquivo;
    }
}