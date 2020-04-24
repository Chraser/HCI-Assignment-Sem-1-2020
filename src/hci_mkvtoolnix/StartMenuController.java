/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

import java.net.URL;
import java.util.ResourceBundle;
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
    private void handleOkButtonAction(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMultiplexer.fxml"));
        AnchorPane root = (AnchorPane) loader.load();        
        Scene scene = new Scene(root);
        Stage stage = HCI_MKVToolNix.getStage();
        stage.setTitle("MKVToolNix GUI Main Menu");
        stage.setScene(scene);
        stage.getIcons().add(new Image("icons/mkvtoolnix-gui.png"));
        stage.show();
    }
    
    @FXML
    private void handleCancelButtonAction(ActionEvent event) throws Exception {
        System.exit(0);
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
