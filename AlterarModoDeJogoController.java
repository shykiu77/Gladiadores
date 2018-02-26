package interfacewarriorsthunderfx;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.*;
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
public class AlterarModoDeJogoController extends Application{
    private static Stage fecharJanela;

    @FXML private AnchorPane anchorPaneAlterarModoJogo;
    @FXML private Label labelAlterarModoDeJogo;
    @FXML private RadioButton radioButtonAlterarTeclado;
    @FXML private ToggleGroup AlterarEscolha;
    @FXML private RadioButton radioButtonAlterarMouse;
    @FXML private Button btnAlterarConfirmar;
    @FXML private Button btnVoltar;
    private int i=0;
    
    @FXML
    public void confirmarAlteracaoModoDeJogo(){
        /*
        Arquivo leituraDeDados = new Arquivo();
        ArrayList<String> dadosArquivo;
        dadosArquivo = leituraDeDados.leituraDeAquivo("configuracoes.txt");
        
        Arquivo salvarEscolha = new Arquivo();
        ArrayList<String> escolha = new ArrayList();
        
        if(dadosArquivo.contains("Não foi possível ler os dados do arquivo!") || dadosArquivo.contains("Arquivo não foi encontrado!")){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Alerta");
            alerta.setHeaderText("Quantidade de players!");
            alerta.setContentText("Por favor, defina a quantidade de players nas Configurações!");
            alerta.showAndWait();
            fecharJanela.close();
        }else{
            
            if(radioButtonAlterarMouse.isSelected()){
                escolha.add("mouse");
            }
            
            if(radioButtonAlterarTeclado.isSelected()){
                escolha.add("teclado");
            }

            salvarEscolha.escreverArquivoModoDeJogo(escolha);
            i++;
            if(i==Integer.parseInt(leituraDeDados.leituraDeAquivo("configuracoes.txt").get(1))){
                i=0;
                MenuJogoController menuDoJogo = new MenuJogoController();
                try {
                    menuDoJogo.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(ModoDeJogoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                fecharJanela.close();
            }
        }
        */
    }
    
    @FXML
    public void voltar(){
        MenuJogoController voltar = new MenuJogoController();
        try {
            voltar.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ConfiguracoesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecharJanela.close();
    }
    /**
     * Initializes the controller class.
     * @throws java.lang.Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("/classesFXML/AlterarModoDeJogo.fxml"));
        
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        
        String css = AlterarModoDeJogoController.class.getResource("/classesCSS/alterarmododejogo.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("N-Cúpula Do Trovão 2D - Modo de Jogo");
        AlterarModoDeJogoController.fecharJanela = primaryStage;
        primaryStage.show();
    }
}