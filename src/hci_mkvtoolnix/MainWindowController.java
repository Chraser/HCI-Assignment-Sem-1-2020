/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chraser
 */
public class MainWindowController implements Initializable {

    @FXML
    private Pane toolPane;
    
    @FXML
    private BorderPane multiplexerSideBar;
    
    @FXML
    private BorderPane infoToolSideBar;
    
    @FXML
    private BorderPane headerEditorSideBar;
    
    @FXML
    private BorderPane chapterEditorSideBar;
    
    @FXML
    private BorderPane jobQueueSideBar;
    
    @FXML
    private BorderPane jobOutputSideBar;
    
    private Pane screenshotToolPane;
    
    private MultiplexerController mc = null;
    
    @FXML
    public void openPreference() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(HCI_MKVToolNix.class.getResource("Preference.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
        PreferenceController controller = loader.getController();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        controller.setStage(stage);
        stage.setTitle("MKVToolNix GUI Preference");
        stage.setScene(scene);
        stage.getIcons().add(new Image("resources/icons/mkvtoolnix-gui-big.png"));
        stage.show();
    }
    
    @FXML
    public void handleMultiplexerClick(MouseEvent event)
    {
        resetSideBarColor();
        multiplexerSideBar.setStyle("-fx-background-color: darkgreen");
        toolPane.getChildren().clear();
        toolPane.getChildren().add(mc);
    }
    
    @FXML
    public void handleInfoToolClick(MouseEvent event)
    {
        resetSideBarColor();
        infoToolSideBar.setStyle("-fx-background-color: darkgreen");
        ImageView image = (ImageView)screenshotToolPane.getChildren().get(0);
        image.setImage(new Image("resources/screenshots/Info-Tool.png"));
        toolPane.getChildren().clear();
        toolPane.getChildren().add(screenshotToolPane);
    }
    
    @FXML
    public void handleHeaderEditorClick(MouseEvent event)
    {
        resetSideBarColor();
        headerEditorSideBar.setStyle("-fx-background-color: darkgreen");
        ImageView image = (ImageView)screenshotToolPane.getChildren().get(0);
        image.setImage(new Image("resources/screenshots/Header-Editor.png"));
        toolPane.getChildren().clear();
        toolPane.getChildren().add(screenshotToolPane);
    }
    
    @FXML
    public void handleChapterEditorClick(MouseEvent event)
    {
        resetSideBarColor();
        chapterEditorSideBar.setStyle("-fx-background-color: darkgreen");
        ImageView image = (ImageView)screenshotToolPane.getChildren().get(0);
        image.setImage(new Image("resources/screenshots/Chapter-Editor.png"));
        toolPane.getChildren().clear();
        toolPane.getChildren().add(screenshotToolPane);
    }
    
    @FXML
    public void handleJobQueueClick(MouseEvent event)
    {
        resetSideBarColor();
        jobQueueSideBar.setStyle("-fx-background-color: darkgreen");
        ImageView image = (ImageView)screenshotToolPane.getChildren().get(0);
        image.setImage(new Image("resources/screenshots/Job-Queue.png"));
        toolPane.getChildren().clear();
        toolPane.getChildren().add(screenshotToolPane);
    }
    
    @FXML
    public void handleJobOutputClick(MouseEvent event)
    {
        resetSideBarColor();
        jobOutputSideBar.setStyle("-fx-background-color: darkgreen");
        ImageView image = (ImageView)screenshotToolPane.getChildren().get(0);
        image.setImage(new Image("resources/screenshots/Job-Output.png"));
        toolPane.getChildren().clear();
        toolPane.getChildren().add(screenshotToolPane);
    }
    
    private void resetSideBarColor()
    {
        multiplexerSideBar.setStyle("-fx-background-color: darkblue");
        infoToolSideBar.setStyle("-fx-background-color: darkblue");
        headerEditorSideBar.setStyle("-fx-background-color: darkblue");
        chapterEditorSideBar.setStyle("-fx-background-color: darkblue");
        jobQueueSideBar.setStyle("-fx-background-color: darkblue");
        jobOutputSideBar.setStyle("-fx-background-color: darkblue");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mc = new MultiplexerController();
        toolPane.getChildren().add(mc);
        FXMLLoader loader = new FXMLLoader(HCI_MKVToolNix.class.getResource("ScreenshotToolPane.fxml"));
        try
        {
            screenshotToolPane = (Pane) loader.load();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }    
    
}
