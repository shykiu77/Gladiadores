package interfacewarriorsthunderfx;

import java.io.File;
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
public class ModoDeJogoController extends Application{
    private static Stage fecharJanela;

    @FXML private AnchorPane anchorPaneModoJogo;
    @FXML private Label labelModoDeJogo;
    @FXML private RadioButton radioButtonTeclado;
    @FXML private ToggleGroup Escolha;
    @FXML private RadioButton radioButtonMouse;
    @FXML private Button btnConfirmar;
    @FXML private Label labelJogador;
    @FXML private Label labelNome;
    @FXML private TextField txtNome;
    private ArrayList<String> nomes = new ArrayList();
    private int i=0;
    ConfiguracoesController enderecoarq = new ConfiguracoesController();
    Arquivo leituraDeDados = new Arquivo();
    ArrayList<String> dadosArquivo = leituraDeDados.leituraDeAquivoConfig(enderecoarq.getEnderecoArquivo());
    private int quantidadeJogadores = Integer.parseInt(leituraDeDados.leituraDeAquivoConfig(enderecoarq.getEnderecoArquivo()).get(1));
    
    @FXML
    public void confirmarModoDeJogo(){
        MenuJogoController menuDoJogo = new MenuJogoController();
        
        Arquivo salvarDados = new Arquivo();
        ArrayList<String> escolha = new ArrayList();
        
        int alteracaoLabel;
        
        if(dadosArquivo.isEmpty()){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Alerta");
            alerta.setHeaderText("Quantidade de players!");
            alerta.setContentText("Por favor, defina a quantidade de players nas Configurações!");
            alerta.showAndWait();
            try {
                menuDoJogo.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(ModoDeJogoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            fecharJanela.close();
        }else{
            
            if(radioButtonMouse.isSelected()){
                escolha.add("mouse");
            }
            
            if(radioButtonTeclado.isSelected()){
                escolha.add("teclado");
            }

            salvarDados.escreverArquivoModoDeJogo(enderecoarq.getEnderecoArquivo(),escolha);
            i++;
            alteracaoLabel = i+1;
            
            String nome = txtNome.getText();
            if(nome.isEmpty())
                nome = "Jogador " + Integer.toString(alteracaoLabel);
            nomes.add(nome);
            
            labelJogador.setText("Jogador " + Integer.toString(alteracaoLabel) + ":");
            txtNome.setText("Jogador " + Integer.toString(alteracaoLabel));
            
            if(i==quantidadeJogadores){
                i=0;
                try {
                    menuDoJogo.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(ModoDeJogoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                salvarDados.escreverArquivo("savesjogadores/"+enderecoarq.getEnderecoArquivo(), nomes);
                fecharJanela.close();
            }
        }
    }
    /**
     * Initializes the controller class.
     * @throws java.lang.Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("/classesFXML/ModoDeJogo.fxml"));
        
        File arquivo = new File("modoDeJogo.txt");
        arquivo.delete();
        
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        
        String css = ModoDeJogoController.class.getResource("/classesCSS/mododejogo.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("N-Cúpula Do Trovão 2D - Modo de Jogo");
        ModoDeJogoController.fecharJanela = primaryStage;
        primaryStage.show();
    }
}