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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ComboBox multiplexerJobComboBox;
    
    @FXML
    private Pane pane;
    
    @FXML
    private TextField destinationFileField;
    
    private InputController inputController;
    
    private Pane outputPane;
    
    private Pane attachmentPane;
    
    private Stage stage;
    
    private List<MultiplexerJobModel> multiplexerJobList = new ArrayList<>();
            
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
        
        MultiplexerJobModel model = new MultiplexerJobModel("NewMovie123.mkv");
        
        model.addFile(new SourceFileModel("HCI Lecture.mp4", "MP4", "16.5GB", "C:/Folder1/User/Folder2"));
        CheckBox codec = new CheckBox("MPEG/AVC/H.264");
        model.addTrack(new TrackModel(codec, "Video", makeNewLabel("Yes"), "eng", "Blah", 
                                      makeNewLabel("Yes"), makeNewLabel("Yes"),
                                      "HCI Lecture.mp4", "C:/Folder1/User/Folder2"));
        codec = new CheckBox("MP3");
        codec.setSelected(true);
        model.addTrack(new TrackModel(codec, "Audio", makeNewLabel("Yes"), "eng", "Blah", 
                                      makeNewLabel("Yes"), makeNewLabel("Yes"),
                                      "HCI Lecture.mp4", "C:/Folder1/User/Folder2"));
        multiplexerJobList.add(model);
        multiplexerJobComboBox.getItems().add(model.getName());
        
        destinationFileField.setText(model.getName());
        
        model = new MultiplexerJobModel("CustomVideo.mp4");
        
        model.addFile(new SourceFileModel("SecretVideo.mkv", "MKV", "60.1GB", "C:/Folder1/User/"));
        codec = new CheckBox("MPEG/H.265");
        codec.setSelected(true);
        model.addTrack(new TrackModel(codec, "Video", makeNewLabel("Yes"), "eng", "Blah", 
                                      makeNewLabel("Yes"), makeNewLabel("Yes"),
                                      "SecretVideo.mkv", "C:/Folder1/User/"));
        codec = new CheckBox("AAC");
        model.addTrack(new TrackModel(codec, "Audio", makeNewLabel("Yes"), "eng", "Blah", 
                                      makeNewLabel("Yes"), makeNewLabel("Yes"),
                                      "SecretVideo.mkv", "C:/Folder1/User/"));
        multiplexerJobList.add(model);
        
        multiplexerJobComboBox.getItems().add(model.getName());
        multiplexerJobComboBox.getSelectionModel().selectFirst();
        changeMultiplexerJob();
    }
    
    private Label makeNewLabel(String s)
    {
        Label label;
        ImageView image;
        if(s.equals("Yes"))
        {
            image = new ImageView(new Image("resources/icons/green-tick.png"));
            image.setFitWidth(20);
            image.setFitHeight(20);
            label = new Label("Yes", image);
        }
        else
        {
            image = new ImageView(new Image("resources/icons/dialog-cancel.png"));
            image.setFitWidth(20);
            image.setFitHeight(20);
            label = new Label("No", image);
        }
        return label;
    }
    
    @FXML
    private void changeMultiplexerJob()
    {
        int index = multiplexerJobComboBox.getSelectionModel().getSelectedIndex();
        MultiplexerJobModel model = multiplexerJobList.get(index);
        inputController.setNewListData(model.getSourceFileList(), model.getTrackList());
    }
            
    @FXML
    private void updateDestinationFile()
    {
        String newName = destinationFileField.getText();
        destinationFileField.setText(newName);
        int index = multiplexerJobComboBox.getSelectionModel().getSelectedIndex();
        multiplexerJobList.get(index).setName(newName);
        ObservableList list = FXCollections.observableArrayList();
        multiplexerJobComboBox.getItems().clear();
        for(MultiplexerJobModel m : multiplexerJobList)
        {
            multiplexerJobComboBox.getItems().add(m.getName());
        }
        multiplexerJobComboBox.getSelectionModel().select(index);
    }
    
    @FXML
    private void selectDestinationFile()
    {
        Stage stage = HCI_MKVToolNix.getStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(stage);
        if(file != null)
        {
            destinationFileField.setText(file.getName());
            int index = multiplexerJobComboBox.getSelectionModel().getSelectedIndex();
            multiplexerJobList.get(index).setName(file.getName());
            ObservableList list = FXCollections.observableArrayList();
            multiplexerJobComboBox.getItems().clear();
            for(MultiplexerJobModel m : multiplexerJobList)
            {
                multiplexerJobComboBox.getItems().add(m.getName());
            }
            multiplexerJobComboBox.getSelectionModel().select(index);
        }
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
            controller.setInputController(inputController);
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
