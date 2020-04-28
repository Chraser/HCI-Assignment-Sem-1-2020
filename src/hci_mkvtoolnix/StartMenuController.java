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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.stage.FileChooser;

/**
 *
 * @author Chraser
 */
public class StartMenuController implements Initializable {
        
    @FXML
    private ComboBox comboBox;
    
    @FXML
    private Button okButton;
        
    @FXML
    private Button cancelButton;
    
    @FXML
    private ListView listView;
    
    private ObservableList fileNames = FXCollections.observableArrayList();
    
    @FXML
    private void handleOkButtonAction(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(HCI_MKVToolNix.class.getResource("MainWindow.fxml"));
        try
        {
            AnchorPane root = (AnchorPane) loader.load();

            Scene scene = new Scene(root);
            Stage stage = HCI_MKVToolNix.getStage();
            stage.setTitle("MKVToolNix GUI Main Menu");
            stage.setScene(scene);
            stage.getIcons().add(new Image("resources/icons/mkvtoolnix-gui-big.png"));
            stage.show();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @FXML
    private void handleCancelButtonAction(ActionEvent event){
        System.exit(0);
    }
    
    @FXML
    private void handleOpenFile()
    {
        Stage stage = HCI_MKVToolNix.getStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        List<File> temp = fileChooser.showOpenMultipleDialog(stage);
        if(temp != null)
        {
            for(File file : temp)
            {
                fileNames.add(file.getName());
            }
            if(fileNames != null)
            {
                listView.setItems(fileNames);
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Label multiplexer = new Label("Multiplexer", new ImageView(new Image("resources/icons/multiplexer-small.png")));
        Label infoTool = new Label("Info Tool", new ImageView(new Image("resources/icons/info-tool-small.png")));
        Label headerEditor = new Label("Header Editor", new ImageView(new Image("resources/icons/header-editor-small.png")));
        Label chapterEditor = new Label("Chapter Editor", new ImageView(new Image("resources/icons/chapter-editor-small.png")));
        ImageView temp = new ImageView(new Image("resources/icons/job-queue-small.png"));
        Label jobQueue = new Label("Job Queue", temp);
        Label jobOutput = new Label("Job Output", new ImageView(new Image("icons/job-output-small.png")));
        comboBox.getItems().addAll(multiplexer, infoTool, headerEditor, chapterEditor, jobQueue, jobOutput);
        comboBox.setCellFactory(new Callback<ListView<Label>,ListCell<Label>>()
        {
            @Override
            public ListCell<Label> call(ListView<Label> l)
            {
                return new CustomLabelList();
            }
        });
        comboBox.setButtonCell(new CustomLabelList());
        comboBox.getSelectionModel().selectFirst();
        comboBox.setVisibleRowCount(6);
        
        String tooltipString = "Multiplexer - Used for modifying video files\n" +
                               "Info tool - Used to display all info of video files\n" +
                               "Header editor - Used to edit header info of video files\n" +
                               "Chapter editor - Used to edit chapter info of video files\n" +
                               "Job queue -  Shows the job queue\n" +
                               "Job output - Shows the list of jobs completed";
        Tooltip tooltip = new Tooltip(tooltipString);
        tooltip.setStyle("-fx-font-size: 20; ");
        comboBox.setTooltip(tooltip);
    }
    
}
