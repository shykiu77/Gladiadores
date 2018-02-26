package interfacewarriorsthunderfx;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Cristiano Lima Oliveira
 */
public class MenuJogoController extends Application implements Initializable{
    private static Stage finalizarJanela;
    @FXML private AnchorPane anchorPaneMenuJogo;
    @FXML private Label labelTitulo;
    @FXML private Button btnJogar;
    @FXML private Button btnModoDeJogo;
    @FXML private Button btnConfiguracoes;
    @FXML private Button btnSair;
    
    @FXML
    public void jogar(){
        Arquivo leitura = new Arquivo();
        ArrayList<String> dadosConfig;
        ArrayList<String> arquivoParaCarregar = leitura.leituraDeAquivo("arquivoCarregado.txt");
        
        if(arquivoParaCarregar.isEmpty()){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Alerta");
            alerta.setHeaderText("Arquivo!");
            alerta.setContentText("Nenhuma configuração para ser carregada, por favor, salve uma configuração!");
            alerta.showAndWait();
        }else{
            if(arquivoParaCarregar.get(0).equals("null")){
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Alerta");
                alerta.setHeaderText("Arquivo!");
                alerta.setContentText("Nenhuma configuração para ser carregada, por favor, salve uma configuração!");
                alerta.showAndWait();
            }else{
                dadosConfig = leitura.leituraDeAquivoConfig(arquivoParaCarregar.get(0));
                int[] vet = new int[5];
                vet[0] = Integer.parseInt(dadosConfig.get(2));
                vet[1] = Integer.parseInt(dadosConfig.get(0));
                vet[2] = Integer.parseInt(dadosConfig.get(4));
                vet[3] = Integer.parseInt(dadosConfig.get(2));
                vet[4] = Integer.parseInt(dadosConfig.get(3));

                Gladiadores_v11 jogar = new Gladiadores_v11();
                try {
                    jogar.start(finalizarJanela, vet);
                } catch (Exception ex) {
                    Logger.getLogger(MenuJogoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @FXML
    public void modoDeJogo() throws Exception{
        AlterarModoDeJogoController tela = new AlterarModoDeJogoController();
        tela.start(new Stage());
        finalizarJanela.close();
    }
    
    @FXML
    public void configuracoes() throws Exception{
        ConfiguracoesController config = new ConfiguracoesController();
        config.start(new Stage());
        finalizarJanela.close();
    }
    
    @FXML
    public void sair(){
        Platform.exit();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File configuracoes = new File("savesconfiguracoes/");
        configuracoes.mkdir();
        File mododejogo = new File("savesmododejogo/");
        mododejogo.mkdir();
        File jogadores = new File("savesjogadores/");
        jogadores.mkdir();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("/classesFXML/MenuJogo.fxml"));
        
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        
        String css = MenuJogoController.class.getResource("/classesCSS/menujogo.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("N-Cúpula Do Trovão 2D");
        MenuJogoController.finalizarJanela = primaryStage;
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}