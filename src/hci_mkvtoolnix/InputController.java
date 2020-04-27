/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Custom Controller class
 *
 * @author Kay Men Yap
 * @student_number 19257442
 */
public class InputController extends AnchorPane
{
    private List<File> fileList = new ArrayList<>();
    
    private ObservableList fileNames = FXCollections.observableArrayList();
    
    public InputController()
    {
        FXMLLoader loader = new FXMLLoader(HCI_MKVToolNix.class.getResource("Input.fxml"));
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
    
    //gets file list from start menu through the input contrller
    public void setFileList(List<File> fileList)
    {
        this.fileList = fileList;
        System.out.println("file obtained");
    }
    
    @FXML
    private void addSourceFiles(ActionEvent event)
    {
        Stage stage = HCI_MKVToolNix.getStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileList.addAll(fileChooser.showOpenMultipleDialog(stage));
    }
    
    @FXML
    public void openAddingSourceFilesPopup(ActionEvent event)
    {
        FXMLLoader loader = new FXMLLoader(HCI_MKVToolNix.class.getResource("AddSourceFiles.fxml"));
        try
        {
            AnchorPane root = (AnchorPane) loader.load();
            AddSourceFilesController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            controller.setStage(stage);
            stage.setTitle("Adding or Appending files");
            stage.setScene(scene);
            stage.getIcons().add(new Image("icons/mkvtoolnix-gui.png"));
            stage.show();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
}
