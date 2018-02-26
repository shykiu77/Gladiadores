package interfacewarriorsthunderfx;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
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
public class CarregarSaveController extends Application implements Initializable{

    @FXML private BorderPane borderPaneCarregarSave;
    @FXML private ListView<String> listaNomes;
    @FXML private AnchorPane anchorPaneCarregarSave;
    @FXML private Button btnDeletar;
    @FXML private Button btnCarregar;
    @FXML private Button btnVoltar;

    private static Stage finalizarJanela;
    
    @FXML
    public void carregarSave(){
        Arquivo escrita = new Arquivo();
        String escolhaSave;
        
        escolhaSave = listaNomes.getSelectionModel().getSelectedItem();
        escrita.escreverArquivoEscolha("arquivoCarregado.txt", escolhaSave);
        finalizarJanela.close();
    }
    
    @FXML
    public void deletarSave(){
        Arquivo leituraEscrita = new Arquivo();
        String escolhaSave;
        File remover;
        
        escolhaSave = listaNomes.getSelectionModel().getSelectedItem();
        
        ArrayList<String> nomes = leituraEscrita.leituraDeAquivo("nomesDosSaves.txt");
        nomes.remove(escolhaSave);
        nomes.remove(null);
        
        leituraEscrita.escreverArquivo("nomesDosSaves.txt", nomes);

        ArrayList<String> nomesAlterados = leituraEscrita.leituraDeAquivo("nomesDosSaves.txt");
        nomesAlterados.remove(null);
        ObservableList<String> names = FXCollections.observableArrayList(nomesAlterados);
        listaNomes.setItems(names);
        
        if(escolhaSave.equals(leituraEscrita.leituraDeAquivo("arquivoCarregado.txt").get(0))){
            leituraEscrita.escreverArquivoEscolha("arquivoCarregado.txt", leituraEscrita.leituraDeAquivo("nomesDosSaves.txt").get(0));
        }
        
        remover = new File("savesconfiguracoes/" + escolhaSave);
        remover.delete();
        remover = new File("savesjogadores/" + escolhaSave);
        remover.delete();
        remover = new File("savesmododejogo/" + escolhaSave);
        remover.delete();
    }
    
    @FXML
    public void voltar(){
        finalizarJanela.close();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Arquivo leitura = new Arquivo();
        ArrayList<String> nomes = leitura.leituraDeAquivo("nomesDosSaves.txt");
        nomes.remove(null);

        ObservableList<String> names = FXCollections.observableArrayList(nomes);
        listaNomes.setItems(names);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("/classesFXML/CarregarSave.fxml"));
        
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        
        String css = CarregarSaveController.class.getResource("/classesCSS/carregarsave.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        finalizarJanela = primaryStage;
        primaryStage.show();
    }
    
}