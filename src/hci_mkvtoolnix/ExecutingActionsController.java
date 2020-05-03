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
    private ComboBox comboBox;
    
    @FXML
    private CheckBox configurationActive;
    
    @FXML
    private TextField audioFileField;
    
    @FXML
    private AnchorPane configPane;
    
    @FXML
    private CheckBox configActive;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private ComboBox typeList;
    
    @FXML
    private CheckBox afterJobSuccess;
    
    @FXML
    private CheckBox afterJobExit;
    
    @FXML
    private CheckBox afterJobStopped;
    
    @FXML
    private TextField volumeField;
    
    @FXML
    private TextField commandLineField;
    
    @FXML
    private AnchorPane audioTypePane;
    
    @FXML
    private AnchorPane executeTypePane;
    
    private List<ActionModel> actionList = new ArrayList<>();
    
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
        typeList.getItems().addAll("Execute a program", "Play an audio file", "Shut down the computer",
                                   "Hibernate the computer", "Sleep the computer", 
                                   "Delete source files for multiplexer jobs");
        
        actionList.add(new ActionModel(true, "Play sound", 1, false, false, false, new String[]{"C:/folder1/audio.ogg","100%"}));
        actionList.add(new ActionModel(true, "Execute blah", 0, false, false, true, new String[]{"-why"}));
        actionList.add(new ActionModel(false, "Shut down pc", 2, false, true, true, null));
        for(ActionModel action : actionList)
        {
            comboBox.getItems().add(action.getName());
        }
        comboBox.getSelectionModel().selectFirst();
        changeAction();
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
        File file = fileChooser.showOpenDialog(stage);
        if(file != null)
        {
            commandLineField.setText(file.getName());
        }
    }
    
    @FXML
    private void changeAction()
    {
        int currentAction = comboBox.getSelectionModel().getSelectedIndex();
        ActionModel action = actionList.get(currentAction);
        configActive.setSelected(action.getConfigActive());
        configPane.setDisable(!action.getConfigActive());
        nameField.setText(action.getName());
        typeList.getSelectionModel().select(action.getType());
        afterJobSuccess.setSelected(action.getJobSuccess());
        afterJobExit.setSelected(action.getJobExit());
        afterJobStopped.setSelected(action.getJobStopped());
        if(action.getExtraInfo() != null)
        {
            if(action.getExtraInfo().length == 1)
            {
               audioTypePane.setVisible(false);
               executeTypePane.setVisible(true);
               commandLineField.setText(action.getExtraInfo()[0]);               
            }
            else
            {
                audioTypePane.setVisible(true);
                executeTypePane.setVisible(false);
                audioFileField.setText(action.getExtraInfo()[0]);
                volumeField.setText(action.getExtraInfo()[1]);
            }
        }
        else
        {
            audioTypePane.setVisible(false);
            executeTypePane.setVisible(false);
        }
    }
    
    @FXML
    private void addAction()
    {
        ActionModel newAction = new ActionModel(true, "Name", 0, false, false, false, new String[]{"Why"});
        actionList.add(newAction);
        comboBox.getItems().add(newAction.getName());
        comboBox.getSelectionModel().selectLast();
    }
    
    @FXML
    private void removeAction()
    {
        int currentAction = comboBox.getSelectionModel().getSelectedIndex();
        actionList.remove(currentAction);
        comboBox.getItems().remove(currentAction);
        if(actionList.size() == 0)
        {
            addAction();
        }
        else
        {
            comboBox.getSelectionModel().selectNext();
        }
    }
    
    @FXML
    private void changeType()
    {
        int currentType = typeList.getSelectionModel().getSelectedIndex();
        int currentAction = comboBox.getSelectionModel().getSelectedIndex();
        ActionModel action = actionList.get(currentAction);
        action.setType(currentType);
        if(action.getExtraInfo() != null)
        {
            if(action.getExtraInfo().length == 1)
            {
               audioTypePane.setVisible(false);
               executeTypePane.setVisible(true);
               commandLineField.setText(action.getExtraInfo()[0]);               
            }
            else
            {
                audioTypePane.setVisible(true);
                executeTypePane.setVisible(false);
                audioFileField.setText(action.getExtraInfo()[0]);
                volumeField.setText(action.getExtraInfo()[1]);
            }
        }
        else
        {
            audioTypePane.setVisible(false);
            executeTypePane.setVisible(false);
        }
    }
    
    @FXML
    private void changeConfig()
    {
        int currentAction = comboBox.getSelectionModel().getSelectedIndex();
        ActionModel action = actionList.get(currentAction);
        action.setConfigActive();
        configPane.setDisable(!action.getConfigActive());
    }
    
    @FXML
    private void changeJobSuccess()
    {
        int currentAction = comboBox.getSelectionModel().getSelectedIndex();
        ActionModel action = actionList.get(currentAction);
        action.setJobSuccess();;
    }
    
    @FXML
    private void changeJobExit()
    {
        int currentAction = comboBox.getSelectionModel().getSelectedIndex();
        ActionModel action = actionList.get(currentAction);
        action.setJobExit();;
    }
    
    @FXML
    private void changeJobStopped()
    {
        int currentAction = comboBox.getSelectionModel().getSelectedIndex();
        ActionModel action = actionList.get(currentAction);
        action.setJobStopped();;
    }
}
