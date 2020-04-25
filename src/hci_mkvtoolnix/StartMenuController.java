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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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
    
    private List<File> fileList = new ArrayList<>();
    
    private ObservableList fileNames = FXCollections.observableArrayList();
    
    @FXML
    private void handleOkButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HCI_MKVToolNix.class.getResource("MainWindow.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
        
        //gets the main window controller to send the file list to multiplexer controller
        MainWindowController nextController = loader.getController();
        nextController.setFileList(fileList);
        
        Scene scene = new Scene(root);
        Stage stage = HCI_MKVToolNix.getStage();
        stage.setTitle("MKVToolNix GUI Main Menu");
        stage.setScene(scene);
        stage.getIcons().add(new Image("icons/mkvtoolnix-gui.png"));
        stage.show();
    }
    
    @FXML
    private void handleCancelButtonAction(ActionEvent event){
        System.exit(0);
    }
    
    @FXML
    private void handleOpenFile() throws Exception {
        Stage stage = HCI_MKVToolNix.getStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        List<File> temp = fileChooser.showOpenMultipleDialog(stage);
        fileList.addAll(temp);
        for(File file : fileList)
        {
            System.out.println(file.getName());
            fileNames.add(file.getName());
        }
        if(fileNames != null)
        {
            listView.setItems(fileNames);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Label multiplexer = new Label("Multiplexer", new ImageView(new Image("icons/multiplexer-small.png")));
        Label infoTool = new Label("Info Tool", new ImageView(new Image("icons/info-tool-small.png")));
        Label headerEditor = new Label("Header Editor", new ImageView(new Image("icons/header-editor-small.png")));
        Label chapterEditor = new Label("Chapter Editor", new ImageView(new Image("icons/chapter-editor-small.png")));
        ImageView temp = new ImageView(new Image("icons/job-queue-small.png"));
        temp.setFitWidth(32);
        temp.setFitHeight(32);
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
    }    
    
}
