/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Kay Men Yap 19257442
 */
public class ExecutingActionsController extends AnchorPane
{
    @FXML
    private ComboBox actionList;
    
    @FXML
    private CheckBox configurationActive;
    
    @FXML
    private TextField audioFileField;
    
    @FXML
    private ComboBox typeList;
    
    @FXML
    private AnchorPane configPane;
    
    private Stage stage;
    
    public ExecutingActionsController()
    {
        FXMLLoader loader = new FXMLLoader(HCI_MKVToolNix.class.getResource("ExecutingActions.fxml"));
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
        typeList.getItems().addAll("");
    }
    
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
    
    @FXML
    private void handleOpenAudioFile()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Audio File");
        File file = fileChooser.showOpenDialog(stage);
        if(file != null)
        {
            audioFileField.setText(file.getName());
        }
    }
    
    @FXML
    private void handleOpenCLFile()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Command Line File");
        fileChooser.showOpenDialog(stage);
    }
    
    @FXML
    private void changeAction()
    {
        int currentAction = actionList.getSelectionModel().getSelectedIndex();
    }
    
    @FXML
    private void changeType(ActionEvent event)
    {
        int currentType = typeList.getSelectionModel().getSelectedIndex();
    }
}
