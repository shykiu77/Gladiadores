package interfacewarriorsthunderfx;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Cristiano Lima Oliveira
 */
public class ConfiguracoesController extends Application implements Initializable {

    private static Stage finalizarJanela;
    @FXML private AnchorPane anchorPaneConfig;
    @FXML private Label labelTituloConfig;
    @FXML private Label labelQuantGladiadores;
    @FXML private TextField txtQuantGladiadores;
    @FXML private Label labelQuantPlayers;
    @FXML private TextField txtQuantPlayers;
    @FXML private Label labelDimensaoCamp;
    @FXML private TextField txtDimensaoCamp;
    @FXML private Label labelQuantArmas;
    @FXML private TextField txtQuantArmas;
    @FXML private Label labelQuantEscudos;
    @FXML private TextField txtQuantEscudos;
    @FXML private Label labelQuantArmadilhas;
    @FXML private TextField txtQuantArmadilhas;
    @FXML private Label labelSave;
    @FXML private TextField txtSave;
    @FXML private Label labelDesvio;
    @FXML private RadioButton radioButtonCruz;
    @FXML private RadioButton radioButton3x3;
    @FXML private Button btnConfigurar;
    @FXML private Button btnVoltar;
    @FXML private Button btnCarregar;
    private static String enderecoArquivo = null;
    
    public String getEnderecoArquivo() {
        return enderecoArquivo;
    }
    
    @FXML
    public void salvarConfig(){
        Arquivo salvandoDados = new Arquivo();
        Arquivo lendoDados = new Arquivo();
        ArrayList<String> dados = new ArrayList();
        
        ArrayList<String> nomesDosArquivos = lendoDados.leituraDeAquivo("nomesDosSaves.txt");
        
        int quantGladiadores=2;
        int quantJogadores=1;
        int quantDimensao=3;
        int quantArmas=2;
        int quantEscudos=2;
        int quantArmadilhas=2;
        
        try{
            quantGladiadores = Integer.parseInt(txtQuantGladiadores.getText());
            quantJogadores = Integer.parseInt(txtQuantPlayers.getText());
            quantDimensao = Integer.parseInt(txtDimensaoCamp.getText());
            quantArmas = Integer.parseInt(txtQuantArmas.getText());
            quantEscudos = Integer.parseInt(txtQuantEscudos.getText());
            quantArmadilhas = Integer.parseInt(txtQuantArmadilhas.getText());
        }catch(Exception e){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Alerta");
            alerta.setHeaderText("Dados Digitados!");
            alerta.setContentText("Por favor, digitar apenas números.");
            alerta.showAndWait();
        }
        
        if(quantDimensao<0 || quantJogadores<0 || quantGladiadores<0 || quantArmas<0 || quantEscudos<0 || quantArmadilhas<0){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Alerta");
            alerta.setHeaderText("Valores Negativos!");
            alerta.setContentText("Por favor, digitar valores maiores ou iguais à 0.");
            alerta.showAndWait();
        }
        else{
            if(radioButtonCruz.isSelected() || radioButton3x3.isSelected()){
                if(quantDimensao < 3){
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Alerta");
                    alerta.setHeaderText("Dimensão do Campo da Cúpula!");
                    alerta.setContentText("Por favor, digitar valor maior ou igual à 3.");
                    alerta.showAndWait();
                }else{
                    if(!nomesDosArquivos.isEmpty()){
                        if(!txtSave.getText().isEmpty()){
                            if(nomesDosArquivos.contains(txtSave.getText() + ".txt")){
                                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                                alerta.setTitle("Alerta");
                                alerta.setHeaderText("Nome de Save!");
                                alerta.setContentText("Este nome já existe! Por favor, digite outro.");
                                alerta.showAndWait();
                            }else{
                                int soma = quantGladiadores+quantArmadilhas+quantArmas+quantEscudos;
                                if(soma<=(quantDimensao*quantDimensao)){
                                    enderecoArquivo = txtSave.getText() + ".txt";

                                    dados.add(Integer.toString(quantGladiadores));
                                    dados.add(Integer.toString(quantJogadores));
                                    dados.add(Integer.toString(quantDimensao));
                                    dados.add(Integer.toString(quantArmas));
                                    dados.add(Integer.toString(quantEscudos));
                                    dados.add(Integer.toString(quantArmadilhas));

                                    if(radioButtonCruz.isSelected()){
                                        dados.add("cruz");
                                    }
                                    if(radioButton3x3.isSelected()){
                                        dados.add("3x3");
                                    }

                                    //System.out.println("savesconfiguracoes/"+enderecoArquivo);
                                    salvandoDados.escreverArquivo("savesconfiguracoes/"+enderecoArquivo, dados);
                                    ModoDeJogoController modoDeJogo = new ModoDeJogoController();
                                        try {
                                            modoDeJogo.start(new Stage());
                                        } catch (Exception ex) {
                                            Logger.getLogger(ConfiguracoesController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    salvandoDados.escreverArquivoEscolha("arquivoCarregado.txt", enderecoArquivo);
                                    salvandoDados.escreverArquivoNomesSaves("nomesDosSaves.txt", enderecoArquivo);
                                    finalizarJanela.close();
                                }
                                else{
                                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                                    alerta.setTitle("Alerta");
                                    alerta.setHeaderText("Dimensão Cúpula!");
                                    alerta.setContentText("Por favor, bote um valor maior para a dimensão da cúpula.");
                                    alerta.showAndWait();
                                }
                            }
                        }
                        else{
                            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                            alerta.setTitle("Alerta");
                            alerta.setHeaderText("Nome de Save!");
                            alerta.setContentText("Por favor, digite um nome de save.");
                            alerta.showAndWait();
                        }
                    }else{
                        if(!txtSave.getText().isEmpty()){
                            int soma = quantGladiadores+quantArmadilhas+quantArmas+quantEscudos;
                            if(soma<=(quantDimensao*quantDimensao)){
                            
                                enderecoArquivo = txtSave.getText() + ".txt";

                                dados.add(Integer.toString(quantGladiadores));
                                dados.add(Integer.toString(quantJogadores));
                                dados.add(Integer.toString(quantDimensao));
                                dados.add(Integer.toString(quantArmas));
                                dados.add(Integer.toString(quantEscudos));
                                dados.add(Integer.toString(quantArmadilhas));

                                if(radioButtonCruz.isSelected()){
                                    dados.add("cruz");
                                }
                                if(radioButton3x3.isSelected()){
                                    dados.add("3x3");
                                }

                                //System.out.println("savesconfiguracoes/"+enderecoArquivo);
                                salvandoDados.escreverArquivo("savesconfiguracoes/"+enderecoArquivo, dados);
                                ModoDeJogoController modoDeJogo = new ModoDeJogoController();
                                    try {
                                        modoDeJogo.start(new Stage());
                                    } catch (Exception ex) {
                                        Logger.getLogger(ConfiguracoesController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                salvandoDados.escreverArquivoEscolha("arquivoCarregado.txt", enderecoArquivo);
                                salvandoDados.escreverArquivoNomesSaves("nomesDosSaves.txt", enderecoArquivo);
                                finalizarJanela.close();
                            }else{
                                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                                    alerta.setTitle("Alerta");
                                    alerta.setHeaderText("Dimensão Cúpula!");
                                    alerta.setContentText("Por favor, bote um valor maior para a dimensão da cúpula.");
                                    alerta.showAndWait();
                            }
                        }
                        else{
                            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                            alerta.setTitle("Alerta");
                            alerta.setHeaderText("Nome de Save!");
                            alerta.setContentText("Por favor, digite um nome de save.");
                            alerta.showAndWait();
                        }
                    }
                }
            }
            else{
                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("Alerta");
                dialogoInfo.setHeaderText("Desvio do Movimento!");
                dialogoInfo.setContentText("Por favor, selecionar um dos desvios de movimentos!");
                dialogoInfo.showAndWait();
            }
        }
    }
    
    @FXML
    public void voltar(){
        MenuJogoController voltar = new MenuJogoController();
        try {
            voltar.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ConfiguracoesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finalizarJanela.close();
    }
    
    @FXML
    public void carregarSave(){
        CarregarSaveController carregar = new CarregarSaveController();
        try {
            carregar.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ConfiguracoesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtQuantGladiadores.setText("2");
        txtQuantPlayers.setText("1");
        txtDimensaoCamp.setText("3");
        txtQuantArmas.setText("2");
        txtQuantEscudos.setText("2");
        txtQuantArmadilhas.setText("2");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("/classesFXML/Configuracoes.fxml"));
        
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        
        String css = ConfiguracoesController.class.getResource("/classesCSS/configuracoes.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("N-Cúpula Do Trovão 2D - Configuracoes");
        ConfiguracoesController.finalizarJanela = primaryStage;
        primaryStage.show();
        
    }   
}