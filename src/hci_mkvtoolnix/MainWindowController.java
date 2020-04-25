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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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
    private MenuBar menuBar;
    
    private MultiplexerController mc = null;
    
    //sends the file list from start menu to the multiplex controller
    public void setFileList(List<File> fileList)
    {
        mc.setFileList(fileList);
    }
    
    @FXML
    public void openPreference() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(HCI_MKVToolNix.class.getResource("Preference.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("MKVToolNix GUI Preference");
        stage.setScene(scene);
        stage.getIcons().add(new Image("icons/mkvtoolnix-gui.png"));
        stage.show();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mc = new MultiplexerController();
        toolPane.getChildren().add(mc);
        /*
        Button button = new Button("Preference");

        CustomMenuItem customMenuItem = new CustomMenuItem();
        customMenuItem.setContent(button);
        customMenuItem.setHideOnClick(false);
        Menu menu1 = menuBar.getMenus().get(0);
        menu1.getItems().add(customMenuItem);
        */
    }    
    
}
