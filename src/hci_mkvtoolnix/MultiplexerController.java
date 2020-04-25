/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chraser
 */
public class MultiplexerController extends AnchorPane {

    @FXML
    private ComboBox projectList;
    
    private List<File> fileList = new ArrayList<>();
    
    private ObservableList fileNames = FXCollections.observableArrayList();
    
    public MultiplexerController()
    {
        FXMLLoader loader = new FXMLLoader(HCI_MKVToolNix.class.getResource("Multiplexer.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    //gets file list from start menu through the main window contrller
    public void setFileList(List<File> fileList)
    {
        this.fileList = fileList;
        System.out.println("file obtained");
    }
    
    @FXML
    private void addSourceFiles()throws IOException {
        /*Stage stage = HCI_MKVToolNix.getStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        List<File> fileList = fileChooser.showOpenMultipleDialog(stage);
        for(File file : fileList)
        {
            System.out.println(file.getName());
            fileNames.add(file.getName());
        }
        if(fileNames != null)
        {
            listView.setItems(fileNames);
        }*/
    }
    
    @FXML
    private void selectDestinationFile()throws IOException {
        /*Stage stage = HCI_MKVToolNix.getStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        List<File> fileList = fileChooser.showOpenMultipleDialog(stage);
        for(File file : fileList)
        {
            System.out.println(file.getName());
            fileNames.add(file.getName());
        }
        if(fileNames != null)
        {
            listView.setItems(fileNames);
        }*/
    }
    
}
