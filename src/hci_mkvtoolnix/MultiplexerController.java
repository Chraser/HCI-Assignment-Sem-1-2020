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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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
public class MultiplexerController extends AnchorPane {

    //also known as the multiplex setting list
    @FXML
    private ComboBox projectList;
    
    @FXML
    private Pane pane;
    
    private InputController inputController;
    
    private Pane outputPane;
    
    private Pane attachmentPane;
    
    private Stage stage;
    
    public MultiplexerController()
    {
        FXMLLoader loader = new FXMLLoader(HCI_MKVToolNix.class.getResource("Multiplexer.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
            
            //load the output pane and attachment pane into memory
            loader = new FXMLLoader(HCI_MKVToolNix.class.getResource("Output.fxml"));
            outputPane = (Pane) loader.load();
            loader = new FXMLLoader(HCI_MKVToolNix.class.getResource("Attachment.fxml"));
            attachmentPane = (Pane) loader.load();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
        //instanties the input controller and add the input fxml into the pane
        inputController = new InputController();
        pane.getChildren().add(inputController);
        
        projectList.getItems().addAll("Project1.mp4", "Project2.mp4", "Project3.mkv");
        projectList.getSelectionModel().selectFirst();
        
    }
    
    @FXML
    private void selectDestinationFile()
    {
        Stage stage = HCI_MKVToolNix.getStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.showOpenDialog(stage);
    }
    
    @FXML
    private void handleInputClick()
    {
        pane.getChildren().clear();
        pane.getChildren().add(inputController);
    }
    
    @FXML
    private void handleOutputClick()
    {
        pane.getChildren().clear();
        pane.getChildren().add(outputPane);
    }
    
    @FXML
    private void handleAttachmentClick()
    {
        pane.getChildren().clear();
        pane.getChildren().add(attachmentPane);
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
            stage.getIcons().add(new Image("resources/icons/mkvtoolnix-gui-big.png"));
            stage.show();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
}
