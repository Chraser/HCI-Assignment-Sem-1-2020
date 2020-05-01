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
import javafx.scene.control.Accordion;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    
    //properties section of fxml references
    
    @FXML
    private Accordion propertiesAccordion;
    
    //general properties
    @FXML
    private CheckBox copyCheckBox;
    
    @FXML 
    private ComboBox nameComboBox;
    
    @FXML
    private ComboBox langComboBox;
    
    @FXML
    private ComboBox defaultComboBox;
    
    @FXML
    private CheckBox forcedCheckBox;
    
    @FXML
    private ComboBox compressionComboBox;
    
    @FXML
    private TextField tagsField;
    
    //timestamp properties
    @FXML
    private ComboBox durationComboBox;
    
    @FXML
    private TextField timestampField;
    
    //video properties
    @FXML
    private AnchorPane videoPane;
    
    private final ToggleGroup videoGroup = new ToggleGroup();
    
    @FXML
    private RadioButton aspectRatioRadio;
    
    @FXML
    private RadioButton displayRadio;
            
    @FXML
    private ComboBox aspectRatioComboBox;
    
    @FXML
    private ComboBox stereoscopyComboBox;
    
    @FXML
    private ComboBox naluComboBox;
    
    //audio properties
    @FXML
    private AnchorPane audioPane;
            
    @FXML
    private ComboBox aacComboBox;
    
    //subtitle properties
    @FXML
    private AnchorPane subtitlePane;
            
    @FXML
    private ComboBox characterSetComboBox;
    
    //miscellaneous properties
    @FXML
    private ComboBox indexingComboBox;
    
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
        
        hardCodedTracks.add(makeNewTrack("MPEG", "Video", "No", "spa", "Video_123", "Yes","Yes", "LegendaryFile.mp4", "C:/Movies/Clips"));
        hardCodedTracks.add(makeNewTrack("AAC", "Audio", "Yes", "spa", "Whooshing sounds", "Yes","Yes", "LegendaryFile.mp4", "C:/Movies/Clips"));
        hardCodedTracks.add(makeNewTrack("H.264", "Video", "Yes", "und", "Clip_1", "Yes","Yes", "BadFile.mkv", "C:/Videos"));
        hardCodedTracks.add(makeNewTrack("MP3", "Audio", "No", "rus", "Drum sounds", "Yes","Yes", "BadFile.mkv", "C:/Videos"));
        hardCodedTracks.add(makeNewTrack("H.265", "Video", "Yes", "eng", "English", "No","No", "Meme.mp4", "C:/Banana"));
        hardCodedTracks.add(makeNewTrack("SRT", "Subtitle", "Yes", "eng", "English subs", "No","Yes", "Meme.mp4", "C:/Banana"));
        
        copyCheckBox.setSelected(true);
        nameComboBox.getItems().addAll("Video", "MP4", "WHY");
        langComboBox.getItems().addAll("Undetermined (und)", "English (eng)", "Spanish (spa)", "Russian (rus)");
        defaultComboBox.getItems().addAll("Determine automatically", "Yes", "No");
        defaultComboBox.getSelectionModel().selectFirst();
        forcedCheckBox.setSelected(true);
        compressionComboBox.getItems().addAll("Determine automatically", "No extra compression", "zlib");
        compressionComboBox.getSelectionModel().selectFirst();
        
        durationComboBox.getItems().addAll("","24p", "25p","60p");
        durationComboBox.getSelectionModel().selectFirst();
        
        aspectRatioRadio.setToggleGroup(videoGroup);
        displayRadio.setToggleGroup(videoGroup);
        aspectRatioRadio.setSelected(true);
        aspectRatioComboBox.getItems().addAll("","4/3", "1.66", "16/9");
        aspectRatioComboBox.getSelectionModel().selectFirst();
        stereoscopyComboBox.getItems().addAll("", "mono", "side by side (left first)");
        naluComboBox.getItems().addAll("Don't change", "Force 2 bytes", "Force 4 bytes");
        naluComboBox.getSelectionModel().selectFirst();
        
        aacComboBox.getItems().addAll("Determine automatically");
        aacComboBox.getSelectionModel().selectFirst();
        
        characterSetComboBox.getItems().addAll("", "ISO-8859-15", "UTF-8", "WINDOWS-1250");
        
        indexingComboBox.getItems().addAll("Determine automatically", "Only for 1 frames", "For all frames", "No cues");
        indexingComboBox.getSelectionModel().selectFirst();
        
        trackTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> 
        {
            propertiesAccordion.setDisable(false);
            int index = trackTable.getSelectionModel().getSelectedIndex();
            TrackModel track = trackList.get(index);
            if(track.getCopyItem().getText().equals("Yes"))
            {
                copyCheckBox.setSelected(true);
            }
            else
            {
                copyCheckBox.setSelected(false);
            }
            nameComboBox.getItems().clear();
            nameComboBox.getItems().addAll(track.getTrackName(),"Video", "MP4", "WHY");
            nameComboBox.getSelectionModel().selectFirst();
            enableAllProperties();
            if(track.getTrackType().equals("Video"))
            {
                audioPane.setDisable(true);
                subtitlePane.setDisable(true);
            }
            else if(track.getTrackType().equals("Audio"))
            {
                videoPane.setDisable(true);
                subtitlePane.setDisable(true);
            }
            else
            {
                videoPane.setDisable(true);
                audioPane.setDisable(true);
            }
        });
        
        propertiesAccordion.setDisable(true);
    }
    
    @FXML
    private void addSourceFiles(ActionEvent event)
    {
        Stage stage = HCI_MKVToolNix.getStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Source File(s)");
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
    
    @FXML private void openTagsFile()
    {
        Stage stage = HCI_MKVToolNix.getStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Tags File");
        File file = fileChooser.showOpenDialog(stage);
        if(file != null)
        {
            tagsField.setText(file.getName());
        }
    }
    
    @FXML private void openTimestampFile()
    {
        Stage stage = HCI_MKVToolNix.getStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Timestamp File");
        File file = fileChooser.showOpenDialog(stage);
        if(file != null)
        {
            timestampField.setText(file.getName());
        }
    }
    
    private void enableAllProperties()
    {
        videoPane.setDisable(false);
        audioPane.setDisable(false);
        subtitlePane.setDisable(false);
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
        
        sourceFileList.add(hardCodedFiles.get(index));
        trackList.add(hardCodedTracks.get(index*2).clone());
        trackList.add(hardCodedTracks.get(index*2+1).clone());
        
        sourceFileOList.setAll(sourceFileList);
        sourceTable.setItems(sourceFileOList);
        trackOList.setAll(trackList);
        trackTable.setItems(trackOList);
    }
    
    private TrackModel makeNewTrack(String codecString, String type, String copy, String lang, String name, 
                                    String defaultT, String forcedT, String file, String directory)
    {
        CheckBox codec = new CheckBox(codecString);
        return new TrackModel(codec, type, makeNewLabel(copy), lang, name, 
                              makeNewLabel(defaultT), makeNewLabel(forcedT),
                              file, directory);
    }
}
