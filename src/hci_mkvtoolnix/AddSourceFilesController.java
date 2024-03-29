/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kay Men Yap 19257442
 */
public class AddSourceFilesController implements Initializable {

    @FXML
    private ComboBox optionsDropDown;
    
    @FXML
    private ComboBox sourceFileDropDown;
    
    @FXML
    private ListView fileListView;
    
    @FXML
    private AnchorPane appendPane;
    
    private Stage stage = null;
    
    private InputController ic;
    
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
    
    public void setInputController(InputController ic)
    {
        this.ic = ic;
    }
    
    @FXML
    private void handleOkAction(ActionEvent event){
        ic.addRandomData();
        stage.close();
    }
    
    @FXML
    private void handleCancelAction(ActionEvent event){
        stage.close();
    }
    
    @FXML
    private void changeOption(ActionEvent event){
        int index = optionsDropDown.getSelectionModel().getSelectedIndex();
        if(index == 3 || index == 4)
        {
            appendPane.setDisable(false);
        }
        else
        {
            appendPane.setDisable(true);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String option1 = "Add as new source files to the current multiplex settings";
        String option2 = "Create new multiplex settings and add to those";
        String option3 = "Create new multiplex settings for each file";
        String option4 = "Append to an existing source file";
        String option5 = "Add as additional parts to an existing source file";
        optionsDropDown.getItems().addAll(option1, option2, option3, option4, option5);
        optionsDropDown.getSelectionModel().selectFirst();
        appendPane.setDisable(true);
        
        sourceFileDropDown.getItems().addAll("File1.mp4","File2.mp4");
        sourceFileDropDown.getSelectionModel().selectFirst();
        
        ObservableList fileList = FXCollections.observableArrayList();
        fileList.add("SomeFile.mkv");
        fileList.add("HCISecretFile.mkv");
        fileListView.setItems(fileList);
    }    
    
}
