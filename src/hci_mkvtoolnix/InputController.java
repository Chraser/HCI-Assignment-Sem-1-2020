/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private TableView sourceTable;
    
    @FXML
    private TableColumn fileNameCol;
    
    @FXML
    private TableColumn fileTypeCol;
    
    @FXML
    private TableColumn fileSizeCol;
    
    @FXML
    private TableColumn directoryCol;
    
    @FXML
    private TableView trackTable;
    
    @FXML
    private TableColumn codecCol;
    
    @FXML
    private TableColumn trackTypeCol;
    
    @FXML
    private TableColumn copyCol;
    
    @FXML
    private TableColumn langCol;
    
    @FXML
    private TableColumn trackNameCol;
    
    @FXML
    private TableColumn defaultTrackCol;
    
    @FXML
    private TableColumn forcedTrackCol;
    
    @FXML
    private TableColumn fileCol;
    
    @FXML
    private TableColumn fileDirectoryCol;
    
    private ObservableList sourceFileList = FXCollections.observableArrayList();
    
    private ObservableList trackList = FXCollections.observableArrayList();
    
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
        
        fileNameCol.setCellValueFactory(new PropertyValueFactory<SourceFileModel, String>("FileName"));
        fileTypeCol.setCellValueFactory(new PropertyValueFactory<SourceFileModel, String>("FileType"));
        fileSizeCol.setCellValueFactory(new PropertyValueFactory<SourceFileModel, String>("FileSize"));
        directoryCol.setCellValueFactory(new PropertyValueFactory<SourceFileModel, String>("Directory"));
        
        codecCol.setCellValueFactory(new PropertyValueFactory<TrackModel, CheckBox>("Codec"));
        trackTypeCol.setCellValueFactory(new PropertyValueFactory<TrackModel, String>("TrackType"));
        copyCol.setCellValueFactory(new PropertyValueFactory<TrackModel, CheckBox>("CopyItem"));
        langCol.setCellValueFactory(new PropertyValueFactory<TrackModel, String>("Language"));
        trackNameCol.setCellValueFactory(new PropertyValueFactory<TrackModel, String>("TrackName"));
        defaultTrackCol.setCellValueFactory(new PropertyValueFactory<TrackModel, Label>("DefaultTrack"));
        forcedTrackCol.setCellValueFactory(new PropertyValueFactory<TrackModel, Label>("ForcedTrack"));
        fileCol.setCellValueFactory(new PropertyValueFactory<TrackModel, String>("SourceFile"));
        fileDirectoryCol.setCellValueFactory(new PropertyValueFactory<TrackModel, String>("Directory"));
        
        sourceFileList.add(new SourceFileModel("HCI Lecture.mp4", "Mp4", "60.1GB", "C:/Folder1/User/Folder2"));
        sourceFileList.add(new SourceFileModel("SecretVideo.mkv", "Matroska", "15.1GB", "C:/Folder1/User/HCI"));
        sourceTable.setItems(sourceFileList);
        
        CheckBox codec = new CheckBox("MPEG/AVC/H.264");
        Label copyItem = makeNewLabel("Yes");
        trackList.add(new TrackModel(codec, "Video", makeNewLabel("Yes"), "eng", "Blah", 
                                     makeNewLabel("Yes"), makeNewLabel("Yes"),
                                     "HCI Lecture.mp4", "C:/Folder1/User/Folder2"));
        
        codec = new CheckBox("MP3");
        copyItem = makeNewLabel("Yes");
        trackList.add(new TrackModel(codec, "Audio", copyItem, "eng", "Blah",
                                     makeNewLabel("Yes"), makeNewLabel("No"),
                                     "HCI Lecture.mp4", "C:/Folder1/User/Folder2"));
        
        trackTable.setItems(trackList);
    }
    
    @FXML
    private void addSourceFiles(ActionEvent event)
    {
        Stage stage = HCI_MKVToolNix.getStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.showOpenMultipleDialog(stage);
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
}
