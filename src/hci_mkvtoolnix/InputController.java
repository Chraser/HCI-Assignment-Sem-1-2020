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
import java.util.Random;
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
 * @author Kay Men Yap 19257442
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
    
    private ObservableList sourceFileOList = FXCollections.observableArrayList();
    
    private ObservableList trackOList = FXCollections.observableArrayList();
    
    private List<SourceFileModel> sourceFileList = new ArrayList<>();
    
    private List<TrackModel> trackList = new ArrayList<>();
    
    private List<SourceFileModel> hardCodedFiles = new ArrayList<>();
    
    private List<TrackModel> hardCodedTracks = new ArrayList<>();
    
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
        
        
        hardCodedFiles.add(new SourceFileModel("LegendaryFile.mp4","Not MP4","420MB","C:/Movies/Clips"));
        hardCodedFiles.add(new SourceFileModel("BadFile.mkv","mkv","100KB","C:/Narnia/Nowhere"));
        hardCodedFiles.add(new SourceFileModel("Meme.mp4","MP4","20GB","D:/Stash"));
        
        hardCodedTracks.add(makeNewTrack("MPEG", "Video", "Yes", "spa", "Espanol", "Yes","Yes", "LegendaryFile.mp4", "C:/Movies/Clips"));
        hardCodedTracks.add(makeNewTrack("AAC", "Audio", "Yes", "spa", "Espanol", "Yes","Yes", "LegendaryFile.mp4", "C:/Movies/Clips"));
        hardCodedTracks.add(makeNewTrack("H.264", "Video", "Yes", "und", "und", "Yes","Yes", "BadFile.mkv", "C:/Videos"));
        hardCodedTracks.add(makeNewTrack("MP3", "Audio", "Yes", "rus", "Russian", "Yes","Yes", "BadFile.mkv", "C:/Videos"));
        hardCodedTracks.add(makeNewTrack("H.265", "Video", "Yes", "eng", "English", "No","No", "Meme.mp4", "C:/Banana"));
        hardCodedTracks.add(makeNewTrack("OGG", "Audio", "Yes", "eng", "English", "No","Yes", "Meme.mp4", "C:/Banana"));
    }
    
    @FXML
    private void addSourceFiles(ActionEvent event)
    {
        Stage stage = HCI_MKVToolNix.getStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        List<File> temp = fileChooser.showOpenMultipleDialog(stage);
        if(temp != null)
        {
            addRandomData();
        }
    }
    
    @FXML
    private void removeSourceFile(ActionEvent event)
    {
        if(sourceTable.getSelectionModel().getSelectedItem() != null)
        {
            int index = sourceTable.getSelectionModel().getSelectedIndex();
            sourceFileList.remove(index);
            sourceFileOList.setAll(sourceFileList);
            sourceTable.setItems(sourceFileOList);
            trackList.remove(index*2);
            trackList.remove(index*2);
            trackOList.setAll(trackList);
            trackTable.setItems(trackOList);
        }
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
    
    public void setNewListData(List<SourceFileModel> sList, List<TrackModel> tList)
    {
        sourceFileList = sList;
        sourceFileOList.setAll(sourceFileList);
        sourceTable.setItems(sourceFileOList);
        
        trackList = tList;
        trackOList.setAll(trackList);
        trackTable.setItems(trackOList);
    }
    
    
    
    public void addRandomData()
    {
        Random random = new Random();
        int index = random.nextInt(2);
        switch(index)
        {
            case 0:
                sourceFileList.add(hardCodedFiles.get(index));
                trackList.add(hardCodedTracks.get(index));
                trackList.add(hardCodedTracks.get(index+1));
                break;
            case 1:
                sourceFileList.add(hardCodedFiles.get(index+1));
                trackList.add(hardCodedTracks.get(index+2));
                trackList.add(hardCodedTracks.get(index+3));
                break;
            case 2:
                sourceFileList.add(hardCodedFiles.get(index+2));
                trackList.add(hardCodedTracks.get(index+4));
                trackList.add(hardCodedTracks.get(index+5));
        }
        sourceFileOList.setAll(sourceFileList);
        sourceTable.setItems(sourceFileOList);
        trackOList.setAll(trackList);
        trackTable.setItems(trackOList);
    }
    
    private TrackModel makeNewTrack(String codecString, String type, String copy, String lang, String name, 
                                    String defaultT, String forcedT, String file, String directory)
    {
        CheckBox codec = new CheckBox("H.265");
        return new TrackModel(codec, type, makeNewLabel(copy), lang, name, 
                              makeNewLabel(defaultT), makeNewLabel(forcedT),
                              file, directory);
    }
}
